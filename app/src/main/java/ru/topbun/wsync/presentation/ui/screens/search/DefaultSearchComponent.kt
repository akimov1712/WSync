package ru.topbun.wsync.presentation.ui.screens.search

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.utils.componentScope

class DefaultSearchComponent @AssistedInject constructor(
    private val storeFactory: SearchStoreFactory,
    @Assisted("openReason") private val openReason: OpenReason,
    @Assisted("onClickToBack") private val onClickToBack: () -> Unit,
    @Assisted("onSavedCity") private val onSavedCity: () -> Unit,
    @Assisted("onOpenForecast") private val onOpenForecast: (City) -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : SearchComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore { storeFactory.create(openReason) }

    init {
        componentScope.launch {
            store.labels.collect{
                when(it){
                    SearchStore.Label.ClickBack -> onClickToBack()
                    is SearchStore.Label.OpenForecast -> onOpenForecast(it.city)
                    SearchStore.Label.SavedToFavorite -> onSavedCity()
                }
            }
        }
    }

    override val model = store.stateFlow

    override fun changeSearchQuery(query: String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun onClickCity(city: City) {
        store.accept(SearchStore.Intent.ClickCity(city))
    }

    override fun onClickSearch() {
        store.accept(SearchStore.Intent.ClickSearch)
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("openReason") openReason: OpenReason,
            @Assisted("onClickToBack") onClickToBack: () -> Unit,
            @Assisted("onSavedCity") onSavedCity: () -> Unit,
            @Assisted("onOpenForecast") onOpenForecast: (City) -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultSearchComponent
    }
}