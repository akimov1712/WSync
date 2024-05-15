package ru.topbun.wsync.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.topbun.wsync.data.local.db.FavouriteDatabase

@Module
class LocalModule {

    @[Provides ApplicationScope]
    fun provideFavoriteDatabase(context: Context) = FavouriteDatabase.getInstance(context)

    @[Provides ApplicationScope]
    fun provideFavoriteDao(db: FavouriteDatabase) = db.favouriteCitiesDao()

}