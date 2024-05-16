package ru.topbun.wsync.presentation.ui.screens.search

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.domain.useCases.ChangeFavoriteCityUseCase
import ru.topbun.wsync.domain.useCases.SearchCityUseCase
import ru.topbun.wsync.presentation.ui.screens.search.SearchStore.Intent
import ru.topbun.wsync.presentation.ui.screens.search.SearchStore.Label
import ru.topbun.wsync.presentation.ui.screens.search.SearchStore.State
import javax.inject.Inject

internal interface SearchStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class ChangeSearchQuery(val query: String): Intent
        data object ClickSearch: Intent
        data object ClickBack: Intent
        data class ClickCity(val city: City): Intent
    }

    data class State(
        val searchQuery: String,
        val openReason: OpenReason,
        val searchState: SearchState
    ){

        sealed interface SearchState{
            data object Initial: SearchState
            data object Loading: SearchState
            data object Error: SearchState
            data object EmptyList: SearchState
            data class Success(val cities: List<City>): SearchState
        }

    }

    sealed interface Label {
        data object ClickBack: Label
        data object SavedToFavorite: Label
        data class OpenForecast(val city: City): Label
    }
}

internal class SearchStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val searchCityUseCase: SearchCityUseCase,
    private val changeFavoriteCityUseCase: ChangeFavoriteCityUseCase
) {

    fun create(openReason: OpenReason): SearchStore =
        object : SearchStore, Store<Intent, State, Label> by storeFactory.create(
            name = "SearchStore",
            initialState = State(
                "",
                OpenReason.RegularSearch,
                State.SearchState.Initial
            ),
            executorFactory = { ExecutorImpl(openReason) },
            reducer = ReducerImpl
        ) {}

    private sealed interface Msg {

        data class ChangeSearchQuery(val query: String): Msg

        data object LoadingSearchResult: Msg

        data object LoadingSearchError: Msg

        data class SearchResultLoaded(val cities: List<City>): Msg

    }

    private inner class ExecutorImpl(val openReason: OpenReason) : CoroutineExecutor<Intent, Nothing, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when(intent){
                is Intent.ChangeSearchQuery -> dispatch(Msg.ChangeSearchQuery(intent.query))
                Intent.ClickBack -> publish(Label.ClickBack)
                is Intent.ClickCity -> {
                    when(openReason){
                        OpenReason.AddToFavorite -> {
                            scope.launch {
                                changeFavoriteCityUseCase.addToFavorite(intent.city)
                                publish(Label.SavedToFavorite)
                            }
                        }
                        OpenReason.RegularSearch -> publish(Label.OpenForecast(intent.city))
                    }
                }
                Intent.ClickSearch -> {
                    scope.launch {
                        dispatch(Msg.LoadingSearchResult)
                        try {
                            val result = searchCityUseCase(state().searchQuery)
                            dispatch(Msg.SearchResultLoaded(result))
                        } catch (e: Exception){
                            dispatch(Msg.LoadingSearchError)
                        }
                    }
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State = when(message){
            is Msg.ChangeSearchQuery -> {
                copy(searchQuery = message.query)
            }

            Msg.LoadingSearchError -> {
                copy(searchState = State.SearchState.Error)
            }

            Msg.LoadingSearchResult -> {
                copy(searchState = State.SearchState.Loading)
            }

            is Msg.SearchResultLoaded -> {
                val cities = message.cities
                val state = if (cities.isNotEmpty()) State.SearchState.Success(cities)
                    else State.SearchState.EmptyList
                copy(searchState = state)
            }

        }
    }
}
