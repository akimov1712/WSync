package ru.topbun.wsync.domain.entity

data class Forecast(
    val currentWeather: Weather,
    val upcoming: List<Weather>
)
