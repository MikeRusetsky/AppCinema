package com.mikerusetsky.appcinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikerusetsky.appcinema.App
import com.mikerusetsky.appcinema.domain.Film
import com.mikerusetsky.appcinema.domain.Interactor
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    val filmsListLiveData: LiveData<List<Film>>
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }

            override fun onFailure() {
                showProgressBar.postValue(false)
            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}