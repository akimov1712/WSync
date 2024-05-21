package ru.topbun.wsync.data.mappers

import ru.topbun.wsync.data.network.dto.currentWeather.CurrentWeatherDto
import ru.topbun.wsync.data.network.dto.currentWeather.CurrentWeatherResponse
import ru.topbun.wsync.domain.entity.CurrentWeather
import ru.topbun.wsync.utils.getIconForWeather

fun CurrentWeatherResponse.toEntity() = CurrentWeather(
    iconRes = getIconForWeather(code = this.current.conditionDto.code, isDay = this.current.isDay),
    tempC = this.current.tempC
)
