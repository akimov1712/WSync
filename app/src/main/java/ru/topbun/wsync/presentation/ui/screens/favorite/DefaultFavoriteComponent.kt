package ru.topbun.wsync.presentation.ui.screens.favorite

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.presentation.ui.screens.search.DefaultSearchComponent
import ru.topbun.wsync.presentation.ui.screens.search.OpenReason
import ru.topbun.wsync.utils.componentScope
import javax.inject.Inject

class DefaultFavoriteComponent @AssistedInject constructor(
    private val storeFactory: FavoriteStoreFactory,
    @Assisted("onClickToCity")private val onClickToCity: (City) -> Unit,
    @Assisted("onClickToFavorite")private val onClickToFavorite: () -> Unit,
    @Assisted("onClickToSearch")private val onClickToSearch: () -> Unit,
    @Assisted("componentContext") private val componentContext: ComponentContext
) : FavoriteComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore { storeFactory.create() }

    init {
        componentScope.launch {
            store.labels.collect{
                when(it){
                    is FavoriteStore.Label.ClickToCity -> onClickToCity(it.city)
                    FavoriteStore.Label.ClickToFavorite -> onClickToFavorite()
                    FavoriteStore.Label.ClickToSearch -> onClickToSearch()
                }
            }
        }
    }

    override val model = store.stateFlow

    override fun onClickSearch() {
        store.accept(FavoriteStore.Intent.ClickToSearch)
    }

    override fun onClickAddFavorite() {
        store.accept(FavoriteStore.Intent.ClickAddToFavorite)
    }

    override fun onClickCity(city: City) {
        store.accept(FavoriteStore.Intent.ClickToCity(city))
    }

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("onClickToCity") onClickToCity: (City) -> Unit,
            @Assisted("onClickToFavorite") onClickToFavorite: () -> Unit,
            @Assisted("onClickToSearch") onClickToSearch: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultFavoriteComponent
    }

}