package com.example.movieapp.data.remote.getsearch

data class Result(
    val first_air_date: String,
    val id: Int,
    val media_type: String,
    val name: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)