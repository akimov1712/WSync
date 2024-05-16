package ru.topbun.wsync.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val id: Int,
    val city: String,
    val country: String,
)
