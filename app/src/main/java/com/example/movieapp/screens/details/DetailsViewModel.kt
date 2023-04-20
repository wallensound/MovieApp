package com.example.movieapp.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.Result
import com.example.movieapp.data.remote.getaccount.AccountStates
import com.example.movieapp.data.remote.getaccount.AddToWatchlist
import com.example.movieapp.data.remote.getaccount.PostAddToWatchlist
import com.example.movieapp.data.remote.getmovie.Credits
import com.example.movieapp.data.remote.getmovie.Movie
import com.example.movieapp.data.remote.getmovie.Similar
import com.example.movieapp.data.remote.gettv.CreditsTV
import com.example.movieapp.data.remote.gettv.SimilarTV
import com.example.movieapp.data.remote.gettv.TV
import com.example.movieapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: Repository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val accountIdKey = intPreferencesKey("accountIdKey")
    private val accountIdFlow: Flow<Int> = dataStore.data
        .map { preferences ->
            // Check if the value is a Int, otherwise return default value.
            preferences[accountIdKey] as? Int ?: 0
        }

    private val sessionIdKey = stringPreferencesKey("sessionIdKey")
    private val sessionIdFlow: Flow<String> = dataStore.data
        .map { preferences ->
            // Check if the value is a String, otherwise return default value.
            preferences[sessionIdKey] as? String ?: "null"
        }

    //Movie
    private val movie = mutableStateOf(
        Movie(
            "",
            emptyList(),
            0,
            "",
            "",
            "",
            0,
            "",
            0.0
        )
    )
    private val credits = mutableStateOf(Credits(emptyList(), 0))
    private val similar = mutableStateOf(Similar(emptyList()))
    val accountStates = mutableStateOf(AccountStates(0, false))
    val addToWatchlist = mutableStateOf(AddToWatchlist(0, "False"))

    fun getMovie(Id: Int): Movie {
        viewModelScope.launch(Dispatchers.IO) {
            val _movie = repository.getMovie(Id)
            if (_movie.isSuccessful && _movie.body() != null) {
                movie.value = _movie.body()!!
            }
        }
        return movie.value
    }

    fun getMovieCredits(Id: Int): Credits {
        viewModelScope.launch(Dispatchers.IO) {
            val _movieCredits = repository.getMovieCredits(Id)
            if (_movieCredits.isSuccessful && _movieCredits.body() != null) {
                credits.value = _movieCredits.body()!!
            }
        }
        return credits.value
    }

    fun getMovieSimilar(Id: Int): List<Result> {
        viewModelScope.launch(Dispatchers.IO) {
            val _movieSimilar = repository.getMovieSimilar(Id)
            if (_movieSimilar.isSuccessful && _movieSimilar.body() != null) {
                similar.value = _movieSimilar.body()!!
            }
        }
        return similar.value.results
    }


    //TV
    private val tv = mutableStateOf(TV("", "", emptyList(), 0, "", "", 0.0))
    private val tvCredits = mutableStateOf(CreditsTV(emptyList(), 0))
    private val tvSimilar = mutableStateOf(SimilarTV(emptyList()))
    fun getTV(Id: Int): TV {
        viewModelScope.launch(Dispatchers.IO) {
            val _tv = repository.getTV(Id)
            if (_tv.isSuccessful && _tv.body() != null) {
                tv.value = _tv.body()!!
            }
        }
        return tv.value
    }

    fun getTVCredits(Id: Int): CreditsTV {
        viewModelScope.launch(Dispatchers.IO) {
            val _tvCredits = repository.getTVCredits(Id)
            if (_tvCredits.isSuccessful && _tvCredits.body() != null) {
                tvCredits.value = _tvCredits.body()!!
            }
        }
        return tvCredits.value
    }

    fun getTVSimilar(Id: Int): List<Result> {
        viewModelScope.launch(Dispatchers.IO) {
            val _tvSimilar = repository.getTVSimilar(Id)
            if (_tvSimilar.isSuccessful && _tvSimilar.body() != null) {
                tvSimilar.value = _tvSimilar.body()!!
            }
        }
        return tvSimilar.value.results
    }

    //Account
    fun getMovieAccountStates(movieId: Int): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            val movieAccountState = repository.getMovieAccountStates(movieId, sessionIdFlow.first())
            if (movieAccountState.isSuccessful && movieAccountState.body() != null) {
                accountStates.value = movieAccountState.body()!!
            }
        }
        return accountStates.value.watchlist
    }

    fun getTVAccountStates(TVId: Int): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            val tvAccountState = repository.getTVAccountStates(TVId, sessionIdFlow.first())
            if (tvAccountState.isSuccessful && tvAccountState.body() != null) {
                accountStates.value = tvAccountState.body()!!
            }
        }
        return accountStates.value.watchlist
    }

    fun postAddToWatchlist(postAddToWatchlist: PostAddToWatchlist) {
        viewModelScope.launch(Dispatchers.IO) {
            val _addToWatchlist = repository.postAddToWatchlist(
                accountId = accountIdFlow.first(),
                sessionId = sessionIdFlow.first(),
                postAddToWatchlist = postAddToWatchlist
            )
            if (_addToWatchlist.isSuccessful && _addToWatchlist.body() != null) {
                addToWatchlist.value = _addToWatchlist.body()!!
                accountStates.value = AccountStates(accountStates.value.id, true)
            }
        }
    }

}