package ru.topbun.wsync.di

import dagger.Binds
import dagger.Module
import ru.topbun.wsync.data.repository.SearchRepositoryImpl
import ru.topbun.wsync.data.repository.WeatherRepositoryImpl
import ru.topbun.wsync.domain.repository.SearchRepository
import ru.topbun.wsync.domain.repository.WeatherRepository

@Module
interface RepositoryModule {

    @[Binds ApplicationScope]
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @[Binds ApplicationScope]
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

}