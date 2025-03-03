package com.example.moviesandtvshowsapplication.repository

import com.example.moviesandtvshowsapplication.WatchModeApi
import com.example.moviesandtvshowsapplication.data.Movie
import com.example.moviesandtvshowsapplication.data.MovieResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

//
//class MTVRepository @Inject constructor(private val apiService: WatchModeApi) {
//    suspend fun getMovies(type: String ): DataOrException<MovieResponse, Boolean, Exception> =
//        try {
//            DataOrException(data = apiService.getMovies(types = type))
//        } catch (e: Exception) {
//            DataOrException(e = e)
//        }
//
//    suspend fun getShows(type: String ): DataOrException<MovieResponse, Boolean, Exception> =
//        try {
//            DataOrException(data = apiService.getShows(types = type))
//        } catch (e: Exception) {
//            DataOrException(e = e)
//        }
//
//    suspend fun getDetails(titleId: Int): DataOrException<Movie, Boolean, Exception> =
//        try {
//            DataOrException(data = apiService.getDetails(titleId = titleId))
//        } catch (e: Exception) {
//            DataOrException(e = e)
//        }
//}

class MTVRepository @Inject constructor(private val apiService: WatchModeApi) {
    fun getMovies(type: String): Single<MovieResponse> =
        apiService.getMovies(types = type).subscribeOn(
            Schedulers.io()
        )

    fun getShows(type: String = "tv_series"): Single<MovieResponse> =
        apiService.getShows(types = type).subscribeOn(Schedulers.io())

    fun getDetails(titleId: Int): Single<Movie> =
        apiService.getDetails(titleId = titleId).subscribeOn(Schedulers.io())
}