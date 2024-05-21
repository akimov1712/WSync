package ru.topbun.wsync.domain.entity

import java.util.Calendar

data class Forecast(
    val currentWeather: CurrentWeatherForecast,
    val thisDayWeather: Weather,
    val upcoming: List<Weather>,
    val date: Calendar,
    val city: String
)
