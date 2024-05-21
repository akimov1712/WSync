package ru.topbun.wsync.data.network.dto.city

import com.google.gson.annotations.SerializedName

data class CityDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val city: String,
    @SerializedName("country") val country: String
)
