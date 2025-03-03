package com.example.moviesandtvshowsapplication.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.moviesandtvshowsapplication.data.Movie
import com.example.moviesandtvshowsapplication.data.MovieResponse
import com.example.moviesandtvshowsapplication.repository.MTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private val repo: MTVRepository) : ViewModel() {

    /* private val _movieList = MutableStateFlow(DataOrException<MovieResponse, Boolean, Exception>())
    val movieList = _movieList.asStateFlow()

    private val _showsList = MutableStateFlow(DataOrException<MovieResponse, Boolean, Exception>())
    val showsList = _showsList.asStateFlow()

    private val _movie = MutableStateFlow(DataOrException<Movie, Boolean, Exception>())
    val movie = _movie.asStateFlow()

    init {
        getMovies("movie")
        getShows("tv_series")
    }

    fun getMovies(type: String) = viewModelScope.launch {
        _movieList.value = DataOrException(loading = true)
        val response = repo.getMovies(type)
        if (response.data?.titles != null) {
            _movieList.value = DataOrException(data = response.data)
        } else {
            _movieList.value = DataOrException(e = response.e)

        }
    }

    fun getShows(type: String) = viewModelScope.launch {
        _showsList.value = DataOrException(loading = true)
        val response = repo.getShows(type)
        if (response.data?.titles != null) {
            _showsList.value = DataOrException(data = response.data)
        } else {
            _showsList.value = DataOrException(e = response.e)

        }
    }

    fun getDetails(titleId: Int) = viewModelScope.launch {
        _movie.value = DataOrException(loading = true)
        val response = repo.getDetails(titleId)
        if (response.data != null) {
            _movie.value = DataOrException(data = response.data)
        } else {
            _movie.value = DataOrException(e = response.e)
        }
    }*/



    private val disposables = CompositeDisposable()
    init {
        getMovies("movie")
        getShows("tv_series")
    }
    val moviesList: MutableState<BehaviorSubject<MovieResponse>> = mutableStateOf(BehaviorSubject.create())
    val showsList: BehaviorSubject<MovieResponse> = BehaviorSubject.create()
    val details: BehaviorSubject<Movie> = BehaviorSubject.create()

    fun getMovies(type: String) {
        val disposable = repo.getMovies(type).observeOn(AndroidSchedulers.mainThread()).subscribe(
            { response ->

                moviesList.value.onNext(response) },
            { error -> moviesList.value.onError(error) }
        )
        disposables.add(disposable)
    }

    fun getShows(type: String) {
        val disposable = repo.getShows(type)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> showsList.onNext(response) },
                { error -> showsList.onError(error) }
            )
        disposables.add(disposable)
    }

    fun getDetails(titleId: Int) {
        val disposable = repo.getDetails(titleId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> details.onNext(response) },
                { error -> details.onError(error) }
            )
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}