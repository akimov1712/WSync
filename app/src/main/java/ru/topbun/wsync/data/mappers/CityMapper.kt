package ru.topbun.wsync.data.mappers

import ru.topbun.wsync.data.network.dto.city.CityDto
import ru.topbun.wsync.domain.entity.City

fun CityDto.toEntity() = City(id, city, country)
