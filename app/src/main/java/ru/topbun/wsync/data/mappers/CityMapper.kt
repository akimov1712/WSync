package ru.topbun.wsync.data.mappers

import ru.topbun.wsync.data.local.model.CityDbModel
import ru.topbun.wsync.data.network.dto.CityDto
import ru.topbun.wsync.domain.entity.City


fun CityDbModel.toEntity() = City(id, city, country)
fun City.toDbo() = CityDbModel(id, city, country)
fun CityDto.toEntity() = City(id, city, country)

fun List<CityDbModel>.toEntities() = map { it.toEntity() }
