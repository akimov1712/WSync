package ru.topbun.wsync.domain.repository

import ru.topbun.wsync.domain.entity.City

interface SearchRepository {

    suspend fun search(query: String): List<City>

}