package ru.topbun.wsync.data.network.dto.forecast

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecastday") val forecastDay: List<WeatherDto>
)
