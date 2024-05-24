package ru.topbun.wsync.domain.entity

import android.graphics.drawable.Drawable
import ru.topbun.wsync.R
import java.util.Calendar
import java.util.Date

data class Weather(
    val date: Date,
    val conditionText: String,
    val iconRes: Int,
    val maxTempC: Float,
    val minTempC: Float,
    val sunset: Date,
    val sunrise: Date,
    val hourlyForecast: List<HourWeather>
)