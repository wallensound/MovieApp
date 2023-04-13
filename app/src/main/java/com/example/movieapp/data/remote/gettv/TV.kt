package com.example.movieapp.data.remote.gettv

data class TV(
    val backdrop_path: String,
    val first_air_date: String,
    val genres: List<Genre>,
    val id: Int,
    val name: String,
    val overview: String,
    val vote_average: Double,
)