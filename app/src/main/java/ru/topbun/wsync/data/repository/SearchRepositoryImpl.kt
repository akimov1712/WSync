package ru.topbun.wsync.data.repository

import ru.topbun.wsync.data.mappers.toEntity
import ru.topbun.wsync.data.network.api.WeatherApiService
import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
) : SearchRepository {

    override suspend fun search(query: String) = apiService.searchCity(query).map { it.toEntity() }

}