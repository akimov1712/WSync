package ru.topbun.wsync.domain.useCases

import ru.topbun.wsync.domain.repository.FavoriteRepository

class GetFavoriteCityUseCase(
    private val repository: FavoriteRepository
) {

    operator fun invoke() = repository.favoriteCities

}