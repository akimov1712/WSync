package ru.topbun.wsync.data.network.dto.currentWeather

import com.google.gson.annotations.SerializedName
import ru.topbun.wsync.data.network.dto.forecast.LocationDto

data class CurrentWeatherResponse(
    @SerializedName("current") val current: CurrentWeatherDto,
)