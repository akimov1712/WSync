package ru.topbun.wsync.data.network.dto.forecast

import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("last_updated_epoch") val dateLong: Long,
    @SerializedName("temp_c") val tempC: Float,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: ConditionDto,
    @SerializedName("wind_kph") val windSpeed: Float,
    @SerializedName("pressure_mb") val pressure: Int,
    @SerializedName("precip_mm") val precipMm: Float,
    @SerializedName("humidity") val humidityPercent: Int,
)