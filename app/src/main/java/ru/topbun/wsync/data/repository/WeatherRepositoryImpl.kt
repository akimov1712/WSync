package ru.topbun.wsync.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import ru.topbun.wsync.data.mappers.toEntity
import ru.topbun.wsync.data.network.api.WeatherApiService
import ru.topbun.wsync.domain.entity.CurrentWeather
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.repository.WeatherRepository
import java.util.Locale
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val context: Context,
    private val apiService: WeatherApiService
): WeatherRepository {
    override suspend fun getWeather(cityId: Int): CurrentWeather {
        return apiService.loadCurrentWeather(PREFIX_CITY_ID + cityId.toString()).toEntity()
    }

    override suspend fun getForecast(cityId: Int) = apiService.loadForecast(
        PREFIX_CITY_ID + cityId.toString()
    ).toEntity()


    override suspend fun getForecast(): Forecast{
        return if (isLocationPermissionGranted()) {
            getForecastFromApi()
        } else {
            apiService.loadForecast("Москва").toEntity()
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun getForecastFromApi(): Forecast {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val lat = location?.latitude ?: 0.0
        val lon = location?.longitude ?: 0.0
        return apiService.loadForecast(
            "$lat,$lon"
        ).toEntity()
    }

    private fun isLocationPermissionGranted() = !(ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED)

    private companion object{
        private const val PREFIX_CITY_ID = "id:"
    }
}