package ru.topbun.wsync.presentation.ui.screens.details

sealed interface OpenReasonDetails {

    data class UsedCityId(val cityId: Int): OpenReasonDetails
    data object Empty: OpenReasonDetails

}