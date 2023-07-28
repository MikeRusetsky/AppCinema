package com.mikerusetsky.appcinema.domain

import android.telecom.Call
import com.mikerusetsky.appcinema.MyApi
import com.mikerusetsky.appcinema.TmdbApi
import com.mikerusetsky.appcinema.data.Entity.TmdbResultsDto
import com.mikerusetsky.appcinema.data.MainRepository
import com.mikerusetsky.appcinema.utils.Converter
import com.mikerusetsky.appcinema.viewmodel.HomeFragmentViewModel
import okhttp3.Response
import javax.security.auth.callback.Callback

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi) {
    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(MyApi.API, "ru-RU", page).enqueue(object : retrofit2.Callback
        <TmdbResultsDto> {
            override fun onResponse(call: retrofit2.Call <TmdbResultsDto>, response: retrofit2.Response<TmdbResultsDto>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
            }

            override fun onFailure(call: retrofit2.Call <TmdbResultsDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
}