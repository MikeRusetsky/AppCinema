package com.mikerusetsky.appcinema.domain

import com.mikerusetsky.appcinema.data.MainRepository

class Interactor (val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}
