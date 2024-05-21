package ru.topbun.wsync.data.network.dto.forecast

import com.google.gson.annotations.SerializedName

data class HourDto(
    @SerializedName("time_epoch") val time: Long,
    @SerializedName("temp_c") val tempC: Float,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: ConditionDto,
)
