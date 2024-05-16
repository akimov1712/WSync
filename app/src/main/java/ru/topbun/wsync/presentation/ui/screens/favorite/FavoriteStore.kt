package ru.topbun.wsync.presentation.ui.screens.favorite

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.domain.useCases.GetFavoriteCityUseCase
import ru.topbun.wsync.domain.useCases.GetWeatherUseCase
import ru.topbun.wsync.presentation.ui.screens.favorite.FavoriteStore.Intent
import ru.topbun.wsync.presentation.ui.screens.favorite.FavoriteStore.Label
import ru.topbun.wsync.presentation.ui.screens.favorite.FavoriteStore.State
import javax.inject.Inject

interface FavoriteStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object ClickToSearch: Intent
        data object ClickAddToFavorite: Intent
        data class ClickToCity(val city: City): Intent

    }

    data class State(
        val cities: List<CityItem>
    ){

        data class CityItem(
            val city: City,
            val state: WeatherState
        )

        sealed interface WeatherState{
            data object Initial: WeatherState
            data object Loading: WeatherState
            data object Error: WeatherState
            data class Success(
                val tempC: Float,
                val iconUrl: String
            ): WeatherState
        }

    }

    sealed interface Label {

        data object ClickToSearch: Label
        data object ClickToFavorite: Label
        data class ClickToCity(val city: City): Label
    }
}

class FavoriteStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getFavoriteCityUseCase: GetFavoriteCityUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) {

    fun create(): FavoriteStore =
        object : FavoriteStore, Store<Intent, State, Label> by storeFactory.create(
            name = "FavoriteStore",
            initialState = State(listOf()),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class FavoriteCitiesLoaded(val cities: List<City>): Action

    }

    private sealed interface Msg {

        data class FavoriteCitiesLoaded(val cities: List<City>): Msg

        data class WeatherSuccessLoad(
            val cityId: Int,
            val tempC: Float,
            val conditionUrlIcon: String
        ): Msg

        data class WeatherErrorLoad(
            val cityId: Int
        ): Msg


        data class WeatherIsLoading(
            val cityId: Int
        ): Msg

    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
               getFavoriteCityUseCase().collect{
                   dispatch(
                       Action.FavoriteCitiesLoaded(it)
                   )
               }
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
        }

        override fun executeAction(action: Action) {
            when(action){
                is Action.FavoriteCitiesLoaded -> {
                    val cities = action.cities
                    dispatch(Msg.FavoriteCitiesLoaded(cities))
                    cities.forEach {
                        scope.launch {
                            loadWeatherForCity(it.id)
                        }
                    }
                }
            }
        }

        private suspend fun loadWeatherForCity(cityId: Int){
            dispatch(Msg.WeatherIsLoading(cityId))
            try {
                val weather = getWeatherUseCase(cityId)
                dispatch(Msg.WeatherSuccessLoad(
                    cityId,
                    weather.tempC,
                    weather.conditionUrl
                ))
            } catch (e: Exception){
                dispatch(Msg.WeatherErrorLoad(cityId))
            }

        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State = when(message){
            is Msg.FavoriteCitiesLoaded -> {
                copy(
                    cities = message.cities.map {
                        State.CityItem(
                            city = it,
                            state = State.WeatherState.Initial
                        )
                    }
                )
            }
            is Msg.WeatherErrorLoad -> {
                copy(
                    cities = cities.map {
                        if (it.city.id == message.cityId) it.copy(state = State.WeatherState.Error)
                        else it
                    }
                )
            }
            is Msg.WeatherIsLoading -> {
                copy(
                    cities = cities.map {
                        if (it.city.id == message.cityId) it.copy(state = State.WeatherState.Loading)
                        else it
                    }
                )
            }
            is Msg.WeatherSuccessLoad -> {
                copy(
                    cities = cities.map {
                        if (it.city.id == message.cityId) it.copy(state = State.WeatherState.Success(
                            tempC = message.tempC,
                            iconUrl = message.conditionUrlIcon
                        ))
                        else it
                    }
                )
            }
        }
    }
}
