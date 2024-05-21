package ru.topbun.wsync.presentation.ui.screens.details

import kotlinx.serialization.Serializable


@Serializable
sealed interface OpenReasonDetails {

    @Serializable
    data class UsedCityId(val cityId: Int): OpenReasonDetails
    @Serializable
    data object Empty: OpenReasonDetails

}