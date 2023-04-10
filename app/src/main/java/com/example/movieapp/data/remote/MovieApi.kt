package com.example.movieapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    //Trending
    @GET("trending/{movieTv}/{weekDay}")
    suspend fun getTrending(
        @Path("movieTv") movieTv: String,
        @Path("weekDay") weekDay: String,
        @Query("api_key") apiKey: String
    ): Response<Trending>

    //Movie
    @GET("/movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<Trending>

    @GET("/movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<Trending>

    @GET("/movie/{movieId}/images")
    suspend fun getMovieImages(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<Trending>

    @GET("/movie/{movieId}/similar")
    suspend fun getMovieSimilar(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<Trending>

    //Account
//    @GET("/movie/{movieId}/account_states")
//    suspend fun getMovieAccountStates(
//        @Path("movieId") movieId: Int,
//        @Query("api_key") apiKey: String
//    ): Response<Trending>
//
//    @GET("/account/{account_id}/watchlist/movies")
//    suspend fun getMovieWatchlist(
//        @Path("account_id") movieId: Int,
//        @Query("api_key") apiKey: String
//    ): Response<Trending>
//
//    @POST("/account/{account_id}/watchlist")
//    suspend fun postAddMovieWatchlist(
//        @Path("account_id") movieId: Int,
//        @Query("api_key") apiKey: String
//    ): Response<Trending>
//
//    //Search
//    @GET("/search/movie")
//    suspend fun getSearch(
//        @Query("api_key") apiKey: String,
//        @Query("query") query: String
//    ): Response<Trending>

}