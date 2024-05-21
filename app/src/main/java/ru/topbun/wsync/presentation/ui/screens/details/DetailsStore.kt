package ru.topbun.wsync.presentation.ui.screens.details

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.useCases.GetForecastCityIdUseCase
import ru.topbun.wsync.domain.useCases.GetForecastLocationUseCase
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore.Intent
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore.Label
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore.State
import javax.inject.Inject

interface DetailsStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickToSearch: Intent
    }

    data class State(
        val openReasonDetailsScreen: OpenReasonDetails,
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
        data object ClickToSearch: Label
    }
}

class DetailsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getForecastCityIdUseCase: GetForecastCityIdUseCase,
    private val getForecastLocationUseCase: GetForecastLocationUseCase
) {

    fun create(openReasonDetailsScreen: OpenReasonDetails): DetailsStore =
        object : DetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "DetailsStore",
            initialState = State(
                openReasonDetailsScreen = openReasonDetailsScreen,
                forecastState = State.ForecastState.Initial
            ),
            bootstrapper = BootstrapperImpl(openReasonDetailsScreen),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class ForecastLoaded(val forecast: Forecast): Action
        data object ForecastStartLoading: Action
        data object ForecastErrorLoading: Action
    }

    private sealed interface Msg {
        data class ForecastLoaded(val forecast: Forecast): Msg
        data object ForecastStartLoading: Msg
        data object ForecastErrorLoading: Msg
    }

    private inner class BootstrapperImpl(private val openReasonDetailsScreen: OpenReasonDetails) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            when(val reason = openReasonDetailsScreen){
                OpenReasonDetails.Empty -> {
                    scope.launch {
                        loadForecast()
                    }
                }
                is OpenReasonDetails.UsedCityId -> {
                    scope.launch {
                        loadForecast(reason.cityId)
                    }
                }
            }

        }

        private suspend fun loadForecast(){
            dispatch(Action.ForecastStartLoading)
            try {
                val forecast = getForecastLocationUseCase()
                dispatch(Action.ForecastLoaded(forecast))
            } catch (e: Exception){
                dispatch(Action.ForecastErrorLoading)
            }
        }

        private suspend fun loadForecast(cityId: Int){
            dispatch(Action.ForecastStartLoading)
            try {
                val forecast = getForecastCityIdUseCase(cityId)
                dispatch(Action.ForecastLoaded(forecast))
            } catch (e: Exception){
                dispatch(Action.ForecastErrorLoading)
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when(intent){
                Intent.ClickToSearch -> publish(Label.ClickToSearch)
            }
        }

        override fun executeAction(action: Action) {
            when(action){
                Action.ForecastErrorLoading -> dispatch(Msg.ForecastErrorLoading)
                is Action.ForecastLoaded -> dispatch(Msg.ForecastLoaded(action.forecast))
                Action.ForecastStartLoading -> dispatch(Msg.ForecastStartLoading)
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State = when(message){
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
