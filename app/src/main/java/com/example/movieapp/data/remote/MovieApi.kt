package com.example.movieapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("trending/movie/week")
    suspend fun getTrending(@Query("api_key") apiKey: String): Response<Trending>
}