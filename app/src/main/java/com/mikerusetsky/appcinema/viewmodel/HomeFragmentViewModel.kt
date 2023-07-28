package com.mikerusetsky.appcinema.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikerusetsky.appcinema.App
import com.mikerusetsky.appcinema.domain.Film
import com.mikerusetsky.appcinema.domain.Interactor
import org.koin.core.KoinComponent
import org.koin.core.inject
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel(), KoinComponent {
    val filmsListLiveData: MutableLiveData<List<Film>> = MutableLiveData()

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }
}