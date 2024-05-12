package ru.topbun.wsync.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.topbun.wsync.data.network.dto.CityDto
import ru.topbun.wsync.data.network.dto.ForecastDto
import ru.topbun.wsync.data.network.dto.WeatherCurrentDto

interface WeatherApiService {

    @GET("search.json?key=75a0de1109a74a3aa54122337241205")
    suspend fun loadCurrentWeather(
        @Query("q") query: String
    ): WeatherCurrentDto


    @GET("forecast.json?key=75a0de1109a74a3aa54122337241205")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") days: Int
    ): ForecastDto

    @GET("search.json?key=75a0de1109a74a3aa54122337241205")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>

}