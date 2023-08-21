package com.mikerusetsky.appcinema.data

import android.content.ContentValues
import android.database.Cursor
import androidx.lifecycle.LiveData
import com.mikerusetsky.appcinema.R
import com.mikerusetsky.appcinema.data.dao.FilmDao
import com.mikerusetsky.appcinema.data.db.DatabaseHelper
import com.mikerusetsky.appcinema.domain.Film
import java.util.concurrent.Executors

class MainRepository (private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): LiveData<List<Film>> = filmDao.getCachedFilms()
}