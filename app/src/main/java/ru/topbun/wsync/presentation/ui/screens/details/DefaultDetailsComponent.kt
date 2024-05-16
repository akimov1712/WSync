package ru.topbun.wsync.presentation.ui.screens.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.presentation.ui.screens.favorite.DefaultFavoriteComponent
import ru.topbun.wsync.utils.componentScope
import javax.inject.Inject

class DefaultDetailsComponent @AssistedInject constructor(
    private val storeFactory: DetailsStoreFactory,
    @Assisted("city") private val city: City,
    @Assisted("onClickToBack") private val onClickToBack: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : DetailsComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore { storeFactory.create(city) }

    init {
        componentScope.launch {
            store.labels.collect{
                when(it){
                    DetailsStore.Label.ClickToBack -> onClickToBack()
                }
            }
        }
    }

    override val model = store.stateFlow

    override fun onClickChangeFavorite() {
        store.accept(DetailsStore.Intent.ClickToChangeFavorite)
    }

    override fun onClickBack() {
        store.accept(DetailsStore.Intent.ClickToBack)
    }

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("city") city: City,
            @Assisted("onClickToBack") onClickToBack: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultDetailsComponent
    }

}