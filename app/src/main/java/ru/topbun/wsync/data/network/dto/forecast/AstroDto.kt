package ru.topbun.wsync.data.network.dto.forecast

import com.google.gson.annotations.SerializedName

data class AstroDto(
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
)
