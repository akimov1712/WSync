package ru.topbun.wsync.data.network.dto

import com.google.gson.annotations.SerializedName
import ru.topbun.wsync.data.network.dto.ConditionDto

data class DayWeatherDto(
    @SerializedName("avgtemp_c") val tempC: Float,
    @SerializedName("condition") val conditionDto: ConditionDto
)
