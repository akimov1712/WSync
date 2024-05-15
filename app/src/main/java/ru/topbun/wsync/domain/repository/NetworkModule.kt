package ru.topbun.wsync.domain.repository

import dagger.Module
import dagger.Provides
import ru.topbun.wsync.data.network.api.ApiFactory
import ru.topbun.wsync.di.ApplicationScope

@Module
class NetworkModule {

    @[Provides ApplicationScope]
    fun provideWeatherApiService() = ApiFactory.weatherApiService

}