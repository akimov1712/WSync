package ru.topbun.wsync.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.topbun.wsync.data.network.dto.currentWeather.CurrentWeatherDto
import ru.topbun.wsync.data.network.dto.forecast.ForecastResponse
import ru.topbun.wsync.data.network.dto.city.CityDto
import ru.topbun.wsync.data.network.dto.currentWeather.CurrentWeatherResponse

interface WeatherApiService {

    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") query: String
    ): CurrentWeatherResponse


    @GET("forecast.json")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") days: Int = 4
    ): ForecastResponse

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>

}