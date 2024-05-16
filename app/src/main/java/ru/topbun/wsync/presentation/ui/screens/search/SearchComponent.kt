package ru.topbun.wsync.presentation.ui.screens.search

import kotlinx.coroutines.flow.StateFlow
import ru.topbun.wsync.domain.entity.City

interface SearchComponent {

    val model: StateFlow<SearchStore.State>

    fun changeSearchQuery(query: String)
    fun onClickCity(city: City)
    fun onClickSearch()
    fun onClickBack()


}