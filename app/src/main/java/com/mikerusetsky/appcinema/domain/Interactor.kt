package com.mikerusetsky.appcinema.domain

import com.mikerusetsky.appcinema.MyApi
import com.mikerusetsky.appcinema.data.MainRepository
import com.mikerusetsky.appcinema.data.PreferenceProvider
import com.mikerusetsky.appcinema.utils.Converter
import com.mikerusetsky.remote_module.TmdbApi
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.kotlin.subscribeBy

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)


    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
        progressBarState.onNext(true)
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), MyApi.API, "ru-RU", page)
            .subscribeOn(Schedulers.io())
            .map {
                Converter.convertApiListToDtoList(it.tmdbFilms)
            }
            .subscribeBy(
                onError = {
                    progressBarState.onNext(false)
                },
                onNext = {
                    progressBarState.onNext(false)
                    repo.putToDb(it)
                }
            )
    }


    fun getSearchResultFromApi(search: String): io.reactivex.rxjava3.core.Observable<List<Film>> = retrofitService.getFilmFromSearch(MyApi.API, "ru-RU", search, 1)
        .map {
            Converter.convertApiListToDtoList(it.tmdbFilms)
        }

    //метод для очистки списка
    fun clearListAfterCategoryChange() {
        Completable.fromSingle<List<Film>> {
            repo.clearDb()
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
    fun getFilmsFromDB(): io.reactivex.rxjava3.core.Observable<List<Film>> = repo.getAllFromDB()
}

