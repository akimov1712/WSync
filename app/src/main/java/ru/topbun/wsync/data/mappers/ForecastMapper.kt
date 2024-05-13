package ru.topbun.wsync.data.mappers

import ru.topbun.wsync.data.network.dto.WeatherForecastDto
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.entity.Weather
import ru.topbun.wsync.utils.toCalendar

fun WeatherForecastDto.toEntity() = Forecast(
    currentWeather = this.current.toEntity(),
    upcoming = this.forecastDto.forecastDay.drop(1).map {
        val dayWeatherDto = it.dayWeatherDto
        Weather(
            tempC = dayWeatherDto.tempC,
            conditionText = dayWeatherDto.conditionDto.text,
            conditionUrl = dayWeatherDto.conditionDto.iconUrl,
            date = it.date.toCalendar(),
        )
    }
)