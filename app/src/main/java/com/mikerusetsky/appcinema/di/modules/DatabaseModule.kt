package com.mikerusetsky.appcinema.di.modules

import android.content.Context
import androidx.room.Room
import com.mikerusetsky.appcinema.AppDatabase
import com.mikerusetsky.appcinema.data.MainRepository
import com.mikerusetsky.appcinema.data.dao.FilmDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideFilmDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}