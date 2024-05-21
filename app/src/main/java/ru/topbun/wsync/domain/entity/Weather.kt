package ru.topbun.wsync.domain.entity

import android.graphics.drawable.Drawable
import ru.topbun.wsync.R
import java.util.Calendar

data class Weather(
    val date: Calendar,
    val conditionText: String,
    val iconRes: Int,
    val maxTempC: Float,
    val minTempC: Float,
    val sunset: Calendar,
    val sunrise: Calendar,
    val hourlyForecast: List<HourWeather>
)