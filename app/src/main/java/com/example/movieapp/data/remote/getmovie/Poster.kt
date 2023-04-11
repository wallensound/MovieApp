package com.example.movieapp.data.remote.getmovie

data class Poster(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: String,
    val vote_average: Int,
    val vote_count: Int,
    val width: Int
)