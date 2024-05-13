package ru.topbun.wsync.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.topbun.wsync.domain.entity.City

@Entity(tableName = "favourite_cities")
data class CityDbModel(
    @PrimaryKey val id: Int,
    val city: String,
    val country: String
)