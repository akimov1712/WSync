package ru.topbun.wsync.presentation.ui.screens.details

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.useCases.ChangeFavoriteCityUseCase
import ru.topbun.wsync.domain.useCases.GetForecastUseCase
import ru.topbun.wsync.domain.useCases.ObserveFavoriteCityUseCase
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore.Intent
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore.Label
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore.State
import ru.topbun.wsync.presentation.ui.screens.search.SearchStore
import javax.inject.Inject

internal interface DetailsStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickToChangeFavorite: Intent
        data object ClickToBack: Intent
    }

    data class State(
        val city: City,
        val isFavorite: Boolean,
        val forecastState: ForecastState
    ){
        sealed interface ForecastState{

            data object Initial: ForecastState
            data object Loading: ForecastState
            data object Error: ForecastState
            data class Success(val forecast: Forecast): ForecastState

        }
    }

    sealed interface Label {
        data object ClickToBack: Label
    }
}

internal class DetailsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getForecastUseCase: GetForecastUseCase,
    private val changeFavoriteCityUseCase: ChangeFavoriteCityUseCase,
    private val observeFavoriteCityUseCase: ObserveFavoriteCityUseCase
) {

    fun create(city: City): DetailsStore =
        object : DetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "DetailsStore",
            initialState = State(
                city = city,
                isFavorite = false,
                forecastState = State.ForecastState.Initial
            ),
            bootstrapper = BootstrapperImpl(city),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class FavoriteStatusChanged(val status: Boolean): Action
        data class ForecastLoaded(val forecast: Forecast): Action
        data object ForecastStartLoading: Action
        data object ForecastErrorLoading: Action
    }

    private sealed interface Msg {
        data class FavoriteStatusChanged(val status: Boolean): Msg
        data class ForecastLoaded(val forecast: Forecast): Msg
        data object ForecastStartLoading: Msg
        data object ForecastErrorLoading: Msg
    }

    private inner class BootstrapperImpl(private val city: City) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                loadForecast()
            }
            scope.launch {
                observeFavoriteStatus()
            }
        }

        private suspend fun observeFavoriteStatus(){
            observeFavoriteCityUseCase(city.id).collect{
                dispatch(Action.FavoriteStatusChanged(it))
            }
        }

        private suspend fun loadForecast(){
            dispatch(Action.ForecastStartLoading)
            try {
                val forecast = getForecastUseCase(city.id)
                dispatch(Action.ForecastLoaded(forecast))
            } catch (e: Exception){
                dispatch(Action.ForecastErrorLoading)
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when(intent){
                Intent.ClickToBack -> publish(Label.ClickToBack)
                Intent.ClickToChangeFavorite -> {
                    val state = state()
                    val isFavorite = state.isFavorite
                    scope.launch {
                        if (isFavorite){
                            changeFavoriteCityUseCase.removeFromFavorite(state.city.id)
                        } else {
                            changeFavoriteCityUseCase.addToFavorite(state.city)
                        }
                    }
                }
            }
        }

        override fun executeAction(action: Action) {
            when(action){
                is Action.FavoriteStatusChanged -> dispatch(Msg.FavoriteStatusChanged(action.status))
                Action.ForecastErrorLoading -> dispatch(Msg.ForecastErrorLoading)
                is Action.ForecastLoaded -> dispatch(Msg.ForecastLoaded(action.forecast))
                Action.ForecastStartLoading -> dispatch(Msg.ForecastStartLoading)
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State = when(message){
            is Msg.FavoriteStatusChanged -> {
                copy(
                    isFavorite = message.status
                )
            }
            Msg.ForecastErrorLoading -> {
                copy(
                    forecastState = State.ForecastState.Error
                )
            }
            is Msg.ForecastLoaded -> {
                copy(
                    forecastState = State.ForecastState.Success(
                        message.forecast
                    )
                )
            }
            Msg.ForecastStartLoading -> {
                copy(
                    forecastState = State.ForecastState.Loading
                )
            }
        }
    }
}
