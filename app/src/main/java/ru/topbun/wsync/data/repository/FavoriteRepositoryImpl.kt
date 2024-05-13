package ru.topbun.wsync.data.repository

import kotlinx.coroutines.flow.map
import ru.topbun.wsync.data.local.db.FavouriteCitiesDao
import ru.topbun.wsync.data.mappers.toDbo
import ru.topbun.wsync.data.mappers.toEntities
import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favouriteCitiesDao: FavouriteCitiesDao
) : FavoriteRepository {

    override val favoriteCities = favouriteCitiesDao.getFavouriteCities().map { it.toEntities() }

    override fun observeIsFavorite(id: Int) = favouriteCitiesDao.observeIsFavourite(id)

    override suspend fun addToFavorite(city: City) = favouriteCitiesDao.addToFavourite(city.toDbo())

    override suspend fun removeFromFavorite(cityId: Int) = favouriteCitiesDao.removeFromFavourite(cityId)
}