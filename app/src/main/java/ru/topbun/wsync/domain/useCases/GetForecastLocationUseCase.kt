package ru.topbun.wsync.domain.useCases

import ru.topbun.wsync.domain.repository.WeatherRepository

class GetForecastLocationUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke() = repository.getForecast()

}