package com.mikerusetsky.appcinema.domain

import android.telecom.Call
import com.mikerusetsky.appcinema.MyApi
import com.mikerusetsky.appcinema.TmdbApi
import com.mikerusetsky.appcinema.data.Entity.TmdbResultsDto
import com.mikerusetsky.appcinema.data.MainRepository
import com.mikerusetsky.appcinema.data.PreferenceProvider
import com.mikerusetsky.appcinema.utils.Converter
import com.mikerusetsky.appcinema.viewmodel.HomeFragmentViewModel
import okhttp3.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)

    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов

        retrofitService.getFilms(getDefaultCategoryFromPreferences(), MyApi.API, "ru-RU",
            page).enqueue (object : retrofit2.Callback <TmdbResultsDto> {
            override fun onResponse (call: retrofit2.Call<TmdbResultsDto>, response: retrofit2.Response<TmdbResultsDto>) {
                //При успехе мы вызываем метод, передаем onSuccess и в этот коллбэк список фильмов
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                //Кладем фильмы в бд
                list.forEach {
                    repo.putToDb(film = it)
                }
                callback.onSuccess(list)
            }

            override fun onFailure(call: retrofit2.Call <TmdbResultsDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
    fun getFilmsFromDB(): List<Film> = repo.getAllFromDB()
}
