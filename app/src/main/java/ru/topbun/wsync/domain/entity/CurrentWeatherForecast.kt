package ru.topbun.wsync.domain.entity

import androidx.compose.ui.graphics.Brush

data class CurrentWeatherForecast(
    val tempC: Float,
    val conditionText: String,
    val iconRes: Int,
    val windSpeed: Float,
    val pressure: Float,
    val precipMm: Float,
    val humidityPercent: Int,
)
