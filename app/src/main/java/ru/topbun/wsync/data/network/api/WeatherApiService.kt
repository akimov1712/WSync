package ru.topbun.wsync.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.topbun.wsync.data.network.dto.CityDto
import ru.topbun.wsync.data.network.dto.ForecastDto
import ru.topbun.wsync.data.network.dto.WeatherCurrentDto
import ru.topbun.wsync.data.network.dto.WeatherForecastDto

interface WeatherApiService {

    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") query: String
    ): WeatherCurrentDto


    @GET("forecast.json")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") days: Int = 4
    ): WeatherForecastDto

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>

}