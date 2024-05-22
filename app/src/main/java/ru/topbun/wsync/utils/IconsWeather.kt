package ru.topbun.wsync.utils

import ru.topbun.wsync.R

private const val DAY = "day"
private const val NIGHT = "night"

private fun String.day() = this + DAY
private fun String.night() = this + NIGHT

private val icon_weathers = mapOf(
    "1000".day() to R.drawable.ic_launcher_background,
    "1000".night() to R.drawable.ic_launcher_background,
    "1003".day() to R.drawable.ic_launcher_background,
    "1006".day() to R.drawable.ic_launcher_background,
    "1009".day() to R.drawable.ic_launcher_background,
    "1030".day() to R.drawable.ic_launcher_background,
    "1063".day() to R.drawable.ic_launcher_background,
    "1066".day() to R.drawable.ic_launcher_background,
    "1069".day() to R.drawable.ic_launcher_background,
    "1072".day() to R.drawable.ic_launcher_background,
    "1087".day() to R.drawable.ic_launcher_background,
    "1114".day() to R.drawable.ic_launcher_background,
    "1117".day() to R.drawable.ic_launcher_background,
    "1135".day() to R.drawable.ic_launcher_background,
    "1147".day() to R.drawable.ic_launcher_background,
    "1150".day() to R.drawable.ic_launcher_background,
    "1153".day() to R.drawable.ic_launcher_background,
    "1168".day() to R.drawable.ic_launcher_background,
    "1171".day() to R.drawable.ic_launcher_background,
    "1180".day() to R.drawable.ic_launcher_background,
    "1183".day() to R.drawable.ic_launcher_background,
    "1186".day() to R.drawable.ic_launcher_background,
    "1189".day() to R.drawable.ic_launcher_background,
    "1192".day() to R.drawable.ic_launcher_background,
    "1195".day() to R.drawable.ic_launcher_background,
    "1198".day() to R.drawable.ic_launcher_background,
    "1201".day() to R.drawable.ic_launcher_background,
    "1204".day() to R.drawable.ic_launcher_background,
    "1207".day() to R.drawable.ic_launcher_background,
    "1210".day() to R.drawable.ic_launcher_background,
    "1213".day() to R.drawable.ic_launcher_background,
    "1216".day() to R.drawable.ic_launcher_background,
    "1219".day() to R.drawable.ic_launcher_background,
    "1222".day() to R.drawable.ic_launcher_background,
    "1225".day() to R.drawable.ic_launcher_background,
    "1237".day() to R.drawable.ic_launcher_background,
    "1240".day() to R.drawable.ic_launcher_background,
    "1243".day() to R.drawable.ic_launcher_background,
    "1246".day() to R.drawable.ic_launcher_background,
    "1249".day() to R.drawable.ic_launcher_background,
    "1252".day() to R.drawable.ic_launcher_background,
    "1255".day() to R.drawable.ic_launcher_background,
    "1258".day() to R.drawable.ic_launcher_background,
    "1261".day() to R.drawable.ic_launcher_background,
    "1264".day() to R.drawable.ic_launcher_background,
    "1273".day() to R.drawable.ic_launcher_background,
    "1276".day() to R.drawable.ic_launcher_background,
    "1279".day() to R.drawable.ic_launcher_background,
    "1282".day() to R.drawable.ic_launcher_background,
)

fun getIconForWeather(code: Int, isDay: Int): Int{
    val stateOut = if (isDay == 1) DAY else NIGHT
    val key = "$code$stateOut"
    return icon_weathers[key] ?: android.R.color.transparent
}