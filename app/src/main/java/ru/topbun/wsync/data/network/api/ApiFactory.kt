package ru.topbun.wsync.data.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.topbun.wsync.BuildConfig

object ApiFactory {

    private const val BASE_URL = "https://api.weatherapi.com/v1/"
    private const val KEY_PARAM = "key"

    private val client = OkHttpClient.Builder()
        .addInterceptor (
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
        .addInterceptor {
            val originalRequest = it.request()
            val newUrl = originalRequest
                .url
                .newBuilder()
                .addQueryParameter(KEY_PARAM,BuildConfig.WEATHER_API_KEY)
                .build()
            val newRequest = originalRequest
                .newBuilder()
                .url(newUrl)
                .build()
            it.proceed(newRequest)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val weatherApiService = retrofit.create(WeatherApiService::class.java)

}