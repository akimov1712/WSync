package ru.topbun.wsync.di

import dagger.Module
import dagger.Provides
import ru.topbun.wsync.domain.repository.SearchRepository
import ru.topbun.wsync.domain.repository.WeatherRepository
import ru.topbun.wsync.domain.useCases.GetForecastCityIdUseCase
import ru.topbun.wsync.domain.useCases.GetForecastLocationUseCase
import ru.topbun.wsync.domain.useCases.GetWeatherUseCase
import ru.topbun.wsync.domain.useCases.SearchCityUseCase

@Module
class UseCaseModule {
    @Provides
    fun provideGetForecastUseCase(repository: WeatherRepository) = GetForecastCityIdUseCase(repository)

    @Provides
    fun provideGetWeatherUseCase(repository: WeatherRepository) = GetWeatherUseCase(repository)

    @Provides
    fun provideSearchCityUseCase(repository: SearchRepository) = SearchCityUseCase(repository)

    @Provides
    fun provideForecastLocationCityUseCase(repository: WeatherRepository) = GetForecastLocationUseCase(repository)

}