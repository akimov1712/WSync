package ru.topbun.wsync.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.topbun.wsync.domain.entity.City

interface FavoriteRepository {

    val favoriteCities: Flow<List<City>>

    fun observeIsFavorite(id: Int): Flow<Boolean>
    suspend fun addToFavorite(city: City)
    suspend fun removeFromFavorite(cityId: Int)

}