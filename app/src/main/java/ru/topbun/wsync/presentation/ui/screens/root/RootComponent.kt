package ru.topbun.wsync.presentation.ui.screens.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.topbun.wsync.presentation.ui.screens.details.DetailsComponent
import ru.topbun.wsync.presentation.ui.screens.favorite.FavoriteComponent
import ru.topbun.wsync.presentation.ui.screens.search.SearchComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child{

        data class Search(val component: SearchComponent): Child
        data class Details(val component: DetailsComponent): Child
        data class Favorite(val component: FavoriteComponent): Child

    }

}