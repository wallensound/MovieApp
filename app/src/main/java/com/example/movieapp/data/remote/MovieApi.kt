package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.getmovie.Credits
import com.example.movieapp.data.remote.getmovie.Movie
import com.example.movieapp.data.remote.getmovie.Similar
import com.example.movieapp.data.remote.gettv.CreditsTV
import com.example.movieapp.data.remote.gettv.SimilarTV
import com.example.movieapp.data.remote.gettv.TV
import retrofit2.Response
import retrofit2.http.GET
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
    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<Movie>

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<Credits>

    @GET("movie/{movieId}/similar")
    suspend fun getMovieSimilar(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<Similar>

    //TV
    @GET("tv/{tv_id}")
    suspend fun getTV(
        @Path("tv_id") TVId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<TV>

    @GET("tv/{tv_id}/credits")
    suspend fun getTVCredits(
        @Path("tv_id") TVId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<CreditsTV>

    @GET("tv/{tv_id}/similar")
    suspend fun getTVSimilar(
        @Path("tv_id") TVId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<SimilarTV>


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