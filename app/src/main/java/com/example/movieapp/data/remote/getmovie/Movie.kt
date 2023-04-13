package com.example.movieapp.data.remote.getmovie

//TODO byt namn till MovieDTO, och sen ha en exstention function som heter MovieDetailsModel.
data class Movie(
    val backdrop_path: String,
    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    val poster_path: Any,
    val release_date: String,
    val runtime: Int,
    val title: String,
    val vote_average: Double,
)