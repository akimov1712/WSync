package ru.topbun.wsync.domain.useCases

import ru.topbun.wsync.domain.repository.FavoriteRepository

class ObserveFavoriteCityUseCase(
    private val repository: FavoriteRepository
) {

    operator fun invoke(id: Int) = repository.observeIsFavorite(id)

}