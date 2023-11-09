package com.mikerusetsky.appcinema.data


import com.mikerusetsky.appcinema.data.dao.FilmDao
import com.mikerusetsky.appcinema.domain.Film
import java.util.concurrent.Executors

class MainRepository (private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): io.reactivex.rxjava3.core.Observable<List<Film>> = filmDao.getCachedFilms()

    fun clearDb() {
        filmDao.clearFilms()
    }
}