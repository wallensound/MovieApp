package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.getaccount.Account
import com.example.movieapp.data.remote.getaccount.MovieWatchList
import com.example.movieapp.data.remote.getaccount.TVWatchList
import com.example.movieapp.data.remote.getmovie.Credits
import com.example.movieapp.data.remote.getmovie.Movie
import com.example.movieapp.data.remote.getmovie.Similar
import com.example.movieapp.data.remote.getsearch.Search
import com.example.movieapp.data.remote.gettv.CreditsTV
import com.example.movieapp.data.remote.gettv.SimilarTV
import com.example.movieapp.data.remote.gettv.TV
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

    //Search
    @GET("search/multi")
    suspend fun getSearch(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false
    ): Response<Search>

    //Account
     @GET("account")
     suspend fun getAccount(
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
     ): Response<Account>

    @GET("movie/{movieId}/account_states")
    suspend fun getMovieAccountStates(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<Trending>

    @GET("tv/{tv_id}/account_states")
    suspend fun getTVAccountStates(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<Trending>

    @GET("account/{account_id}/watchlist/movies")
    suspend fun getMovieWatchlist(
        @Path("account_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieWatchList>

    @GET("account/{account_id}/watchlist/tv")
    suspend fun getTVWatchlist(
        @Path("account_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<TVWatchList>

    @POST("account/{account_id}/watchlist")
    suspend fun postAddToWatchlist(
        @Path("account_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Response<Trending>

}