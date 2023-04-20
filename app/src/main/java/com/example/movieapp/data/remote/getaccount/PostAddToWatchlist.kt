package com.example.movieapp.data.remote.getaccount

data class PostAddToWatchlist(
    val media_id: Int,
    val media_type: String,
    val watchlist: Boolean = true
)