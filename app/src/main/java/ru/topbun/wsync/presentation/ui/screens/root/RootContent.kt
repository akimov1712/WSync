package ru.topbun.wsync.presentation.ui.screens.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.topbun.wsync.presentation.ui.screens.details.DetailsContent
import ru.topbun.wsync.presentation.ui.screens.splash.SplashContent
import ru.topbun.wsync.presentation.ui.theme.WSyncTheme

@Composable
fun RootContent(component: RootComponent) {
    WSyncTheme {
        Children(stack = component.stack) {
            when(val instance = it.instance){
                is RootComponent.Child.Details -> DetailsContent(component = instance.component)
                is RootComponent.Child.Splash -> SplashContent(component = instance.component)
            }
        }
    }
}