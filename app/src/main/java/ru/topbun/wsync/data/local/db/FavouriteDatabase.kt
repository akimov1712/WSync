package ru.topbun.wsync.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.topbun.wsync.data.local.model.CityDbModel

@Database(entities = [CityDbModel::class], version = 1, exportSchema = false)
abstract class FavouriteDatabase : RoomDatabase() {

    abstract fun favouriteCitiesDao(): FavouriteCitiesDao

    companion object {

        private const val DB_NAME = "FavouriteDatabase"
        private var INSTANCE: FavouriteDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDatabase(context).also { INSTANCE = it } }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context, FavouriteDatabase::class.java, DB_NAME).build()
    }
}
