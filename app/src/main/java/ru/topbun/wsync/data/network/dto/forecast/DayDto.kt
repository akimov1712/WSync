package ru.topbun.wsync.data.network.dto.forecast

import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("maxtemp_c") val maxTempC: Float,
    @SerializedName("mintemp_c") val minTempC: Float,
    @SerializedName("condition") val condition: ConditionDto,
)