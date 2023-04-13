package com.example.movieapp.data.remote

data class Result(
    val first_air_date: String,
    val id: Int,
    val name: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)