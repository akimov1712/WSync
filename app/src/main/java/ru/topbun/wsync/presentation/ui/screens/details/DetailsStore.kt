package ru.topbun.wsync.presentation.ui.screens.details

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore.Intent
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore.Label
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore.State

internal interface DetailsStore : Store<Intent, State, Label> {

    sealed interface Intent {
    }

    data class State(val todo: Unit)

    sealed interface Label {
    }
}

internal class DetailsStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): DetailsStore =
        object : DetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "DetailsStore",
            initialState = State(Unit),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {

        }

        override fun executeAction(action: Action) {

        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State = State(Unit)
    }
}
