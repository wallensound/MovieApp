package com.example.movieapp.data.remote.getmovie

data class Images(
    val backdrops: List<Backdrop>,
    val id: Int,
    val posters: List<Poster>
)