package ru.topbun.wsync.data.network.dto.forecast

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("date_epoch") val date: Long,
    @SerializedName("day") val day: DayDto,
    @SerializedName("astro") val astro: AstroDto,
    @SerializedName("hour") val hours: List<HourDto>,
)
