package ru.topbun.wsync.data.network.dto

import com.google.gson.annotations.SerializedName
import ru.topbun.wsync.data.network.dto.DayDto

data class ForecastDto(
    @SerializedName("forecastday") val forecastDay: List<DayDto>
)
