package ru.topbun.wsync.domain.useCases

import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.domain.repository.FavoriteRepository

class ChangeFavoriteCityUseCase(
    private val repository: FavoriteRepository
) {

    suspend fun addToFavorite(city: City) = repository.addToFavorite(city)
    suspend fun removeFromFavorite(cityId: Int) = repository.removeFromFavorite(cityId)

}