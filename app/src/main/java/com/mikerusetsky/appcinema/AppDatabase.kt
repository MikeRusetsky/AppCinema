package com.mikerusetsky.appcinema

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mikerusetsky.appcinema.data.dao.FilmDao
import com.mikerusetsky.appcinema.domain.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}
