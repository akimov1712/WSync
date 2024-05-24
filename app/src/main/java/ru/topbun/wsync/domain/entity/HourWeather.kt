package ru.topbun.wsync.domain.entity

import java.util.Calendar
import java.util.Date

data class HourWeather(
    val time: Date,
    val iconRes: Int,
    val tempC: Float
)
