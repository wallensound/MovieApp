package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.RetrofitInstance.movieApi

class Repository {
    private val apiKey = "59994be5ad141c30929fe77d167e677a"
    suspend fun getTrendingMovieWeek() = movieApi.getTrending(movieTv = "movie", weekDay = "week", apiKey)
    suspend fun getTrendingMovieDay() = movieApi.getTrending(movieTv = "movie", weekDay = "day", apiKey)
    suspend fun getTrendingTvWeek() = movieApi.getTrending(movieTv = "tv", weekDay = "week", apiKey)
    suspend fun getTrendingTvDay() = movieApi.getTrending(movieTv = "tv", weekDay = "day", apiKey)

}