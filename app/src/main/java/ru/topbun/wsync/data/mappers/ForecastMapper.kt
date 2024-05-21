package ru.topbun.wsync.data.mappers

import ru.topbun.wsync.data.network.dto.forecast.CurrentDto
import ru.topbun.wsync.data.network.dto.forecast.ForecastResponse
import ru.topbun.wsync.data.network.dto.forecast.HourDto
import ru.topbun.wsync.data.network.dto.forecast.WeatherDto
import ru.topbun.wsync.domain.entity.CurrentWeatherForecast
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.entity.HourWeather
import ru.topbun.wsync.domain.entity.Weather
import ru.topbun.wsync.utils.getIconForWeather
import ru.topbun.wsync.utils.toCalendar
import java.text.SimpleDateFormat

fun ForecastResponse.toEntity() = Forecast(
    currentWeather = current.toEntity(),
    thisDayWeather = forecast.forecastDay.first().toEntity(),
    upcoming = forecast.forecastDay.drop(1).map { it.toEntity() },
    date = current.dateLong.toCalendar(),
    city = location.name
)

private fun CurrentDto.toEntity() = CurrentWeatherForecast(
    tempC = tempC,
    conditionText = condition.text,
    iconRes = getIconForWeather(condition.code, isDay),
    windSpeed = windSpeed,
    pressure = windSpeed,
    precipMm = precipMm,
    humidityPercent = humidityPercent
)

private fun WeatherDto.toEntity() = Weather(
    date = date.toCalendar(),
    conditionText = day.condition.text,
    iconRes = getIconForWeather(day.condition.code, 1),
    maxTempC = day.maxTempC,
    minTempC = day.minTempC,
    sunset = astro.sunset.convertAmericanDateToLong().toCalendar(),
    sunrise = astro.sunrise.convertAmericanDateToLong().toCalendar(),
    hourlyForecast = hours.toEntityList()
)

private fun List<HourDto>.toEntityList() = map {
    HourWeather(
        time = it.time.toCalendar(),
        iconCode = getIconForWeather(it.condition.code, it.isDay),
        tempC = it.tempC
    )
}

private fun String.convertAmericanDateToLong(): Long{
    val inputFormat = SimpleDateFormat("hh:mm a")
    val date = inputFormat.parse(this)
    return date.time
}