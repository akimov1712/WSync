package ru.topbun.wsync.data.network.dto.forecast

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("location") val location: LocationDto,
    @SerializedName("current") val current: CurrentDto,
    @SerializedName("forecast") val forecast: ForecastDto
)
