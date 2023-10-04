package com.mikerusetsky.appcinema.viewmodel



import androidx.lifecycle.ViewModel
import com.mikerusetsky.appcinema.App
import com.mikerusetsky.appcinema.domain.Film
import com.mikerusetsky.appcinema.domain.Interactor
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    val filmsListData: io.reactivex.rxjava3.core.Observable<List<Film>>
    val showProgressBar: BehaviorSubject<Boolean>

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1)
    }

    fun getSearchResult(search: String) = interactor.getSearchResultFromApi(search)
}