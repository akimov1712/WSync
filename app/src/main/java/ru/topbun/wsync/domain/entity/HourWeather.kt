package ru.topbun.wsync.domain.entity

import java.util.Calendar

data class HourWeather(
    val time: Calendar,
    val iconRes: Int,
    val tempC: Float
)
