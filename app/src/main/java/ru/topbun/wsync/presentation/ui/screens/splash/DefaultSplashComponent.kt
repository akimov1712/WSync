package ru.topbun.wsync.presentation.ui.screens.splash

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.topbun.wsync.utils.componentScope

class DefaultSplashComponent @AssistedInject constructor(
    private val storeFactory: SplashStoreFactory,
    @Assisted("onPermissionCheckCompleted") private val onPermissionCheckCompleted: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
): ComponentContext by componentContext, SplashComponent {

    private val store = instanceKeeper.getStore { storeFactory.create() }

    init {
        componentScope.launch {
            store.labels.collect{
                when(it){
                    SplashStore.Label.PermissionCheckCompleted -> onPermissionCheckCompleted()
                }
            }
        }
    }

    override fun permissionCheckCompleted() {
        store.accept(SplashStore.Intent.PermissionCheckCompleted)
    }

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("onPermissionCheckCompleted") onPermissionCheckCompleted: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultSplashComponent
    }
}