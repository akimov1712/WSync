package ru.topbun.wsync.presentation.ui.screens.favorite

import kotlinx.coroutines.flow.StateFlow
import ru.topbun.wsync.domain.entity.City

interface FavoriteComponent {

    val model: StateFlow<FavoriteStore.State>

    fun onClickSearch()
    fun onClickAddFavorite()
    fun onClickCity(city: City)


}