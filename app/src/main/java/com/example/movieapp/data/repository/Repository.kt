package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.RetrofitInstance.movieApi

class Repository {
    private val apiKey = "59994be5ad141c30929fe77d167e677a"

    //Trending
    suspend fun getTrendingMovieWeek() = movieApi.getTrending(movieTv = "movie", weekDay = "week", apiKey)
    suspend fun getTrendingMovieDay() = movieApi.getTrending(movieTv = "movie", weekDay = "day", apiKey)
    suspend fun getTrendingTvWeek() = movieApi.getTrending(movieTv = "tv", weekDay = "week", apiKey)
    suspend fun getTrendingTvDay() = movieApi.getTrending(movieTv = "tv", weekDay = "day", apiKey)

    //Movie
    suspend fun getMovie(movieId: Int) = movieApi.getMovie(movieId, apiKey)
    suspend fun getMovieCredits(movieId: Int) = movieApi.getMovieCredits(movieId, apiKey)
    suspend fun getMovieSimilar(movieId: Int) = movieApi.getMovieSimilar(movieId, apiKey)

    //TV
    suspend fun getTV(TVId: Int) = movieApi.getTV(TVId, apiKey)
    suspend fun getTVCredits(TVId: Int) = movieApi.getTVCredits(TVId, apiKey)
    suspend fun getTVSimilar(TVId: Int) = movieApi.getTVSimilar(TVId, apiKey)

    //Search
    suspend fun getSearch(query: String) = movieApi.getSearch(query = query, apiKey = apiKey)

}