package ru.topbun.wsync.data.network.dto.currentWeather

import com.google.gson.annotations.SerializedName
import ru.topbun.wsync.data.network.dto.forecast.ConditionDto
import ru.topbun.wsync.data.network.dto.forecast.LocationDto

data class CurrentWeatherDto(
    @SerializedName("condition") val conditionDto: ConditionDto,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("temp_c") val tempC: Float,

)