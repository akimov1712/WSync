package ru.topbun.wsync.presentation.ui.screens.details

import kotlinx.coroutines.flow.StateFlow

interface DetailsComponent {

    val model: StateFlow<DetailsStore.State>

    fun onClickSearch()

}