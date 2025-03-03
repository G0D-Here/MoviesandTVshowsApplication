package com.example.moviesandtvshowsapplication.data

data class MovieResponse(
    val titles: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val year : Int,
    val type: String,
    val poster: String,
    val plot_overview:String
)
