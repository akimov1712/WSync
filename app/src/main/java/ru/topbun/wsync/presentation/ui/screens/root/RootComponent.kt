package ru.topbun.wsync.presentation.ui.screens.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.topbun.wsync.presentation.ui.screens.details.DetailsComponent
import ru.topbun.wsync.presentation.ui.screens.splash.SplashComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child{
        data class Details(val component: DetailsComponent): Child
        data class Splash(val component: SplashComponent): Child
    }

}