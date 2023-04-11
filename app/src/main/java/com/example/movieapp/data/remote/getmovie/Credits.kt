package com.example.movieapp.data.remote.getmovie

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)