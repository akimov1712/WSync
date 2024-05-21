package ru.topbun.wsync.domain.entity

data class CurrentWeatherForecast(
    val tempC: Float,
    val conditionText: String,
    val iconRes: Int,
    val windSpeed: Float,
    val pressure: Float,
    val precipMm: Float,
    val humidityPercent: Int,
)
