package com.example.moviesandtvshowsapplication

import com.example.moviesandtvshowsapplication.data.Movie
import com.example.moviesandtvshowsapplication.data.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
//
//const val api_key = "knJEVq87sLP6KqwdrvNRjlsnvA8wa4pqabYaho6V"
//
//interface WatchModeApi {
//    @GET("list-titles/")
//    suspend fun getMovies(
//        @Query("apiKey") apiKey: String = "knJEVq87sLP6KqwdrvNRjlsnvA8wa4pqabYaho6V",
//        @Query("types") types: String
//    ): MovieResponse
//
//    @GET("list-titles/")
//    suspend fun getShows(
//        @Query("apiKey") apiKey: String = "knJEVq87sLP6KqwdrvNRjlsnvA8wa4pqabYaho6V",
//        @Query("types") types: String
//    ): MovieResponse
//
//    @GET("title/{title_id}/details/")
//    suspend fun getDetails(
//        @Path("title_id") titleId: Int,
//        @Query("apiKey") apiKey: String = "knJEVq87sLP6KqwdrvNRjlsnvA8wa4pqabYaho6V"
//    ): Movie
//}


const val api_key = "knJEVq87sLP6KqwdrvNRjlsnvA8wa4pqabYaho6V"

interface WatchModeApi {
    @GET("list-titles/")
    fun getMovies(
        @Query("apiKey") apiKey: String = "knJEVq87sLP6KqwdrvNRjlsnvA8wa4pqabYaho6V",
        @Query("types") types: String
    ): Single<MovieResponse>

    @GET("list-titles/")
    fun getShows(
        @Query("apiKey") apiKey: String = "knJEVq87sLP6KqwdrvNRjlsnvA8wa4pqabYaho6V",
        @Query("types") types: String
    ): Single<MovieResponse>

    @GET("title/{title_id}/details/")
    fun getDetails(
        @Path("title_id") titleId: Int,
        @Query("apiKey") apiKey: String = "knJEVq87sLP6KqwdrvNRjlsnvA8wa4pqabYaho6V"
    ): Single<Movie>
}