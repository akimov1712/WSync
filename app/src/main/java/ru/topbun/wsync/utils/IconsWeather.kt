package ru.topbun.wsync.utils

import ru.topbun.wsync.R

private const val DAY = "day"
private const val NIGHT = "night"

private fun String.day() = this + DAY
private fun String.night() = this + NIGHT

private val icon_weathers = mapOf(
    "1000".day() to R.drawable.image_clean_d,
    "1000".night() to R.drawable.image_clean_n,

    "1003".day() to R.drawable.image_low_cloud_d,
    "1003".night() to R.drawable.image_low_cloud_n,

    "1006".day() to R.drawable.image_cloud,
    "1006".night() to R.drawable.image_cloud,

    "1009".day() to R.drawable.image_very_clouds,
    "1009".night() to R.drawable.image_very_clouds,

    "1030".day() to R.drawable.image_fog,
    "1030".night() to R.drawable.image_fog,

    "1063".day() to R.drawable.image_rain_d,
    "1063".night() to R.drawable.image_rain_n,

    "1066".day() to R.drawable.image_snow,
    "1066".night() to R.drawable.image_snow,

    "1069".day() to R.drawable.image_snow,
    "1069".night() to R.drawable.image_snow,

    "1072".day() to R.drawable.image_snow,
    "1072".night() to R.drawable.image_snow,

    "1087".day() to R.drawable.image_thunder,
    "1087".night() to R.drawable.image_thunder,

    "1114".day() to R.drawable.image_snow,
    "1114".night() to R.drawable.image_snow,

    "1117".day() to R.drawable.image_snow,
    "1117".night() to R.drawable.image_snow,

    "1135".day() to R.drawable.image_fog,
    "1135".night() to R.drawable.image_fog,

    "1147".day() to R.drawable.image_fog,
    "1147".night() to R.drawable.image_fog,

    "1150".day() to R.drawable.image_rain_d,
    "1150".night() to R.drawable.image_rain_n,

    "1153".day() to R.drawable.image_rain_d,
    "1153".night() to R.drawable.image_rain_n,

    "1168".day() to R.drawable.image_rain_d,
    "1168".night() to R.drawable.image_rain_n,

    "1171".day() to R.drawable.image_rainfall,
    "1171".night() to R.drawable.image_rainfall,

    "1180".day() to R.drawable.image_rain_d,
    "1180".night() to R.drawable.image_rain_n,

    "1183".day() to R.drawable.image_rain_d,
    "1183".night() to R.drawable.image_rain_n,

    "1186".day() to R.drawable.image_rain_d,
    "1186".night() to R.drawable.image_rain_n,

    "1189".day() to R.drawable.image_rainfall,
    "1189".night() to R.drawable.image_rainfall,

    "1192".day() to R.drawable.image_rainfall,
    "1192".night() to R.drawable.image_rainfall,

    "1195".day() to R.drawable.image_rainfall,
    "1195".night() to R.drawable.image_rainfall,

    "1198".day() to R.drawable.image_rainfall,
    "1198".night() to R.drawable.image_rainfall,

    "1201".day() to R.drawable.image_rainfall,
    "1201".night() to R.drawable.image_rainfall,

    "1204".day() to R.drawable.image_snow,
    "1204".night() to R.drawable.image_snow,

    "1207".day() to R.drawable.image_snow,
    "1207".night() to R.drawable.image_snow,

    "1210".day() to R.drawable.image_snow,
    "1210".night() to R.drawable.image_snow,

    "1213".day() to R.drawable.image_snow,
    "1213".night() to R.drawable.image_snow,

    "1216".day() to R.drawable.image_snow,
    "1216".night() to R.drawable.image_snow,

    "1219".day() to R.drawable.image_snow,
    "1219".night() to R.drawable.image_snow,

    "1222".day() to R.drawable.image_snow,
    "1222".night() to R.drawable.image_snow,

    "1225".day() to R.drawable.image_snow,
    "1225".night() to R.drawable.image_snow,

    "1237".day() to R.drawable.image_snow,
    "1237".night() to R.drawable.image_snow,

    "1240".day() to R.drawable.image_rain_d,
    "1240".night() to R.drawable.image_rain_n,

    "1243".day() to R.drawable.image_rainfall,
    "1243".night() to R.drawable.image_rainfall,

    "1246".day() to R.drawable.image_rainfall,
    "1246".night() to R.drawable.image_rainfall,

    "1249".day() to R.drawable.image_rainfall,
    "1249".night() to R.drawable.image_rainfall,

    "1252".day() to R.drawable.image_rainfall,
    "1252".night() to R.drawable.image_rainfall,

    "1255".day() to R.drawable.image_rainfall,
    "1255".night() to R.drawable.image_rainfall,

    "1258".day() to R.drawable.image_snow,
    "1258".night() to R.drawable.image_snow,

    "1261".day() to R.drawable.image_rain_d,
    "1261".night() to R.drawable.image_rain_n,

    "1264".day() to R.drawable.image_rainfall,
    "1264".night() to R.drawable.image_rainfall,

    "1273".day() to R.drawable.image_thunder,
    "1273".night() to R.drawable.image_thunder,

    "1276".day() to R.drawable.image_thunder,
    "1276".night() to R.drawable.image_thunder,

    "1279".day() to R.drawable.image_thunder,
    "1279".night() to R.drawable.image_thunder,

    "1282".day() to R.drawable.image_thunder,
    "1282".night() to R.drawable.image_thunder,
)

fun getIconForWeather(code: Int, isDay: Int): Int{
    val stateOut = if (isDay == 1) DAY else NIGHT
    val key = "$code$stateOut"
    return icon_weathers[key] ?: android.R.color.transparent
}