package ru.topbun.wsync.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.topbun.wsync.domain.repository.FavoriteRepository
import ru.topbun.wsync.domain.repository.SearchRepository
import ru.topbun.wsync.domain.repository.WeatherRepository
import ru.topbun.wsync.domain.useCases.ChangeFavoriteCityUseCase
import ru.topbun.wsync.domain.useCases.GetFavoriteCityUseCase
import ru.topbun.wsync.domain.useCases.GetForecastUseCase
import ru.topbun.wsync.domain.useCases.GetWeatherUseCase
import ru.topbun.wsync.domain.useCases.ObserveFavoriteCityUseCase
import ru.topbun.wsync.domain.useCases.SearchCityUseCase

@Module
class UseCaseModule {

    @Provides
    fun provideChangeFavoriteCityUseCase(repository: FavoriteRepository) = ChangeFavoriteCityUseCase(repository)

    @Provides
    fun provideGetFavoriteCityUseCase(repository: FavoriteRepository) = GetFavoriteCityUseCase(repository)

    @Provides
    fun provideGetForecastUseCase(repository: WeatherRepository) = GetForecastUseCase(repository)

    @Provides
    fun provideGetWeatherUseCase(repository: WeatherRepository) = GetWeatherUseCase(repository)

    @Provides
    fun provideObserveFavoriteCityUseCase(repository: FavoriteRepository) = ObserveFavoriteCityUseCase(repository)

    @Provides
    fun provideSearchCityUseCase(repository: SearchRepository) = SearchCityUseCase(repository)


}