package ru.topbun.wsync.presentation.ui.screens.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.topbun.wsync.presentation.ui.screens.details.DetailsContent
import ru.topbun.wsync.presentation.ui.screens.favorite.FavoriteContent
import ru.topbun.wsync.presentation.ui.screens.search.SearchContent
import ru.topbun.wsync.presentation.ui.theme.WSyncTheme

@Composable
fun RootContent(component: RootComponent) {
    WSyncTheme {
        Children(stack = component.stack) {
            when(val instance = it.instance){
                is RootComponent.Child.Details -> DetailsContent(instance.component)
                is RootComponent.Child.Favorite -> FavoriteContent(instance.component)
                is RootComponent.Child.Search -> SearchContent(instance.component)
            }
        }
    }
}