package ru.topbun.wsync.domain.repository

import ru.topbun.wsync.data.network.dto.WeatherForecastDto
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(cityId: Int): Weather
    suspend fun getForecast(cityId: Int): Forecast

}