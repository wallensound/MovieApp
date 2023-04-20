package com.example.movieapp.screens.account

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.Result
import com.example.movieapp.data.remote.authentication.PostRequestToken
import com.example.movieapp.data.remote.authentication.RequestToken
import com.example.movieapp.data.remote.authentication.Session
import com.example.movieapp.data.remote.getaccount.Account
import com.example.movieapp.data.remote.getaccount.MovieWatchList
import com.example.movieapp.data.remote.getaccount.TVWatchList
import com.example.movieapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AccountViewModel(
    private val repository: Repository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val accountIdKey = intPreferencesKey("accountIdKey")
    private val sessionIdKey = stringPreferencesKey("sessionIdKey")
    val sessionIdFlow: Flow<String> = dataStore.data
        .map { preferences ->
            // Check if the value is a String, otherwise return default value.
            preferences[sessionIdKey] as? String ?: "null"
        }

    val requestToken = mutableStateOf(RequestToken("", "", false))
    private val session = mutableStateOf(Session("", false))
    private val account = mutableStateOf(Account(0, ""))
    private val movieWatchlist = mutableStateOf(MovieWatchList(emptyList()))
    private val tvWatchlist = mutableStateOf(TVWatchList(emptyList()))
    val filterState = mutableStateOf(true)

    fun setState() {
        filterState.value = !filterState.value
    }

    fun getRequestToken() {

        requestToken.value = RequestToken("", "", false)
        viewModelScope.launch(Dispatchers.IO) {
            val _requestToken = repository.getRequestToken()
            if (_requestToken.body() != null) {
                requestToken.value = _requestToken.body()!!
                Log.d("TAG", "getRequestToken: $requestToken")
            }
        }
    }

    fun postCreateSession() {
        viewModelScope.launch(Dispatchers.IO) {
            val postRequestToken = PostRequestToken(requestToken.value.request_token)
            Log.d("TAG", "getRequestToken: $postRequestToken")
            val _session = repository.postCreateSession(postRequestToken)
            Log.d("TAG", "getRequestToken: $_session")
            if (_session.body() != null) {
                session.value = _session.body()!!
                Log.d("TAG", "getRequestToken: $session")
                dataStore.edit {
                    it[sessionIdKey] = session.value.session_id
                }
            }
        }
    }

    fun getAccount(): Account {
            viewModelScope.launch(Dispatchers.IO) {
                val _account = repository.getAccount(sessionIdFlow.first())
                if (_account.body() != null) {
                    account.value = _account.body()!!
                    Log.d("TAG", "getRequestToken: $account")
                    dataStore.edit {
                        it[accountIdKey] = account.value.id
                    }
                }
            }
        return account.value
    }

    fun getMovieWatchlist(): List<Result> {
        viewModelScope.launch(Dispatchers.IO) {
            val _movieWatchlist =
                repository.getMovieWatchlist(account.value.id, sessionIdFlow.first())
            if (_movieWatchlist.isSuccessful && _movieWatchlist.body() != null) {
                movieWatchlist.value = _movieWatchlist.body()!!
            }
        }
        return movieWatchlist.value.results
    }

    fun getTVWatchlist(): List<Result> {
        viewModelScope.launch(Dispatchers.IO) {
            val _tvWatchlist = repository.getTVWatchlist(account.value.id, sessionIdFlow.first())
            if (_tvWatchlist.isSuccessful && _tvWatchlist.body() != null) {
                tvWatchlist.value = _tvWatchlist.body()!!
            }
        }
        return tvWatchlist.value.results
    }
}