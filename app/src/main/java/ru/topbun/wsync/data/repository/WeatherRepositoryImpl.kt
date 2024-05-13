package ru.topbun.wsync.data.repository

import ru.topbun.wsync.data.mappers.toEntity
import ru.topbun.wsync.data.network.api.WeatherApiService
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.entity.Weather
import ru.topbun.wsync.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
): WeatherRepository {
    override suspend fun getWeather(cityId: Int): Weather {
        return apiService.loadCurrentWeather(PREFIX_CITY_ID + cityId.toString()).toEntity()
    }

    override suspend fun getForecast(cityId: Int) = apiService.loadForecast(
        PREFIX_CITY_ID + cityId.toString()
    ).toEntity()

    private companion object{
        private const val PREFIX_CITY_ID = "id:"
    }
}