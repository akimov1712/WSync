package ru.topbun.wsync.utils

import ru.topbun.wsync.R

private const val DAY = "day"
private const val NIGHT = "night"
private val icon_weathers = mapOf(
    "113$DAY" to R.drawable.ic_launcher_background,
    "113$NIGHT" to R.drawable.ic_launcher_background
)

fun getIconForWeather(code: Int, isDay: Int): Int{
    val stateOut = if (isDay == 1) DAY else NIGHT
    val key = "$code$stateOut"
    return icon_weathers[key] ?: android.R.color.transparent
}