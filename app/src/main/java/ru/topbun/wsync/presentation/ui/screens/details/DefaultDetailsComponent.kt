package ru.topbun.wsync.presentation.ui.screens.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.topbun.wsync.utils.componentScope

class DefaultDetailsComponent @AssistedInject constructor(
    private val storeFactory: DetailsStoreFactory,
    @Assisted("openReasonDetailsScreen") private val openReasonDetailsScreen: OpenReasonDetails,
    @Assisted("onClickToSearch") private val onClickToSearch: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : DetailsComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore { storeFactory.create(openReasonDetailsScreen) }

    init {
        componentScope.launch {
            store.labels.collect{
                when(it){
                    DetailsStore.Label.ClickToSearch -> onClickToSearch()
                }
            }
        }
    }

    override val model = store.stateFlow


    override fun onClickSearch() {
        store.accept(DetailsStore.Intent.ClickToSearch)
    }

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("openReasonDetailsScreen") openReasonDetailsScreen: OpenReasonDetails,
            @Assisted("onClickToSearch") onClickToSearch: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultDetailsComponent
    }

}