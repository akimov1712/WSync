package ru.topbun.wsync.presentation.ui.screens.splash

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import javax.inject.Inject


interface SplashStore: Store<SplashStore.Intent, SplashStore.State, SplashStore.Label>{

    sealed interface Intent{
        data object PermissionCheckCompleted: Intent
    }

    class State()

    sealed interface Label{
        data object PermissionCheckCompleted: Label
    }

}

class SplashStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
){

    fun create(): SplashStore =
        object : SplashStore, Store<SplashStore.Intent, SplashStore.State, SplashStore.Label>
        by storeFactory.create(
            name = "SplashStore",
            initialState = SplashStore.State(),
            executorFactory = { ExecutorImpl() },
            reducer = ReducerImpl
        ) {}

    private inner class ExecutorImpl : CoroutineExecutor<SplashStore.Intent, Nothing, SplashStore.State, Nothing, SplashStore.Label>() {

        override fun executeIntent(intent: SplashStore.Intent) {
            when(intent){
                SplashStore.Intent.PermissionCheckCompleted -> publish(SplashStore.Label.PermissionCheckCompleted)
            }
        }
    }

    private object ReducerImpl : Reducer<SplashStore.State, Nothing> {
        override fun SplashStore.State.reduce(msg: Nothing) = SplashStore.State()
    }

}