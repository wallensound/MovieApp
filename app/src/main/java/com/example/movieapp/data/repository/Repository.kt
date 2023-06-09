package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.remote.authentication.PostRequestToken
import com.example.movieapp.data.remote.getaccount.PostAddToWatchlist

class Repository(private val movieApi: MovieApi) {

    private val apiKey = "59994be5ad141c30929fe77d167e677a"

    //Trending
    suspend fun getTrendingMovieWeek() =
        movieApi.getTrending(movieTv = "movie", weekDay = "week", apiKey)

    suspend fun getTrendingMovieDay() =
        movieApi.getTrending(movieTv = "movie", weekDay = "day", apiKey)

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

    //Account
    suspend fun getAccount(sessionId: String) = movieApi.getAccount(apiKey, sessionId)
    suspend fun getMovieWatchlist(accountId: Int, sessionId: String) =
        movieApi.getMovieWatchlist(accountId = accountId, sessionId = sessionId, apiKey = apiKey)

    suspend fun getTVWatchlist(accountId: Int, sessionId: String) =
        movieApi.getTVWatchlist(accountId = accountId, sessionId = sessionId, apiKey = apiKey)

    suspend fun getMovieAccountStates(movieId: Int, sessionId: String) =
        movieApi.getMovieAccountStates(movieId = movieId, apiKey = apiKey, sessionId = sessionId)

    suspend fun getTVAccountStates(TVId: Int, sessionId: String) =
        movieApi.getTVAccountStates(TVId = TVId, apiKey = apiKey, sessionId = sessionId)

    suspend fun postAddToWatchlist(
        accountId: Int,
        sessionId: String,
        postAddToWatchlist: PostAddToWatchlist
    ) =
        movieApi.postAddToWatchlist(
            accountId = accountId,
            sessionId = sessionId,
            addToWatchList = postAddToWatchlist,
            apiKey = apiKey
        )


    //Authentication
    suspend fun getRequestToken() = movieApi.getRequestToken(apiKey)
    suspend fun postCreateSession(postRequestToken: PostRequestToken) =
        movieApi.postCreateSession(postRequestToken, apiKey)

}