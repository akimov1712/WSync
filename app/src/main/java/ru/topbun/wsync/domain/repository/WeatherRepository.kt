package ru.topbun.wsync.domain.repository

import ru.topbun.wsync.domain.entity.CurrentWeather
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(cityId: Int): CurrentWeather
    suspend fun getForecast(cityId: Int): Forecast
    suspend fun getForecast(): Forecast
}