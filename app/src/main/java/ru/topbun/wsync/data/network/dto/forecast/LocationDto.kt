package ru.topbun.wsync.data.network.dto.forecast

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name") val name: String
)
