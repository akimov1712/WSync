package ru.topbun.wsync.domain.entity

import java.util.Calendar
import java.util.Date

data class Forecast(
    val currentWeather: CurrentWeatherForecast,
    val thisDayWeather: Weather,
    val upcoming: List<Weather>,
    val date: Date,
    val city: String
)
