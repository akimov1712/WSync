package ru.topbun.wsync.data.mappers

import ru.topbun.wsync.data.network.dto.WeatherCurrentDto
import ru.topbun.wsync.data.network.dto.WeatherDto
import ru.topbun.wsync.domain.entity.Weather
import ru.topbun.wsync.utils.toCalendar
import java.util.Calendar
import java.util.Date

fun WeatherCurrentDto.toEntity() = current.toEntity()

fun WeatherDto.toEntity() = Weather(
    tempC = tempC,
    conditionText = conditionDto.text,
    conditionUrl = conditionDto.iconUrl.toIconUrl(),
    date = date.toCalendar()
)

private fun String.toIconUrl() = "https:$this".replace("64x64", "128x128")