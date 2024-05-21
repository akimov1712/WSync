package ru.topbun.wsync.domain.useCases

import ru.topbun.wsync.domain.repository.WeatherRepository

class GetForecastCityIdUseCase(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(cityId: Int) = repository.getForecast(cityId)

}