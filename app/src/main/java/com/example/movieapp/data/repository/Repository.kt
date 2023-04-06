package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.RetrofitInstance.movieApi

class Repository {
    private val apiKey = "59994be5ad141c30929fe77d167e677a"
    suspend fun getTrending() = movieApi.getTrending(apiKey)
}