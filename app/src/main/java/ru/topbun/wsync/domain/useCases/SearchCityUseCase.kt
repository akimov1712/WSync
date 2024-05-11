package ru.topbun.wsync.domain.useCases

import ru.topbun.wsync.domain.repository.SearchRepository

class SearchCityUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(query: String) = repository.search(query)

}