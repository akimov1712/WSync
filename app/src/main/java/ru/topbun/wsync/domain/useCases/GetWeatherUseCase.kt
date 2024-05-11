package ru.topbun.wsync.domain.useCases

import ru.topbun.wsync.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(cityId: Int) = repository.getWeather(cityId)

}