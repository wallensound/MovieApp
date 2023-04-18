package com.example.movieapp.screens.account

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {
    private val repository = Repository()
    val requestToken = mutableStateOf(RequestToken("","",false))
    val session = mutableStateOf(Session("", false))
    private val account = mutableStateOf(Account(0,""))
    private val movieWatchlist = mutableStateOf(MovieWatchList(emptyList()))
    private val tvWatchlist = mutableStateOf(TVWatchList(emptyList()))
    val filterState = mutableStateOf(true)

    override fun onCleared() {
        Log.d("hejan", "hejsan")
        super.onCleared()
    }
    fun setState() {
        filterState.value = !filterState.value
    }

    fun getRequestToken() {
        requestToken.value = RequestToken("","",false)
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
            }
        }
    }

    fun getAccount(): Account {
        if (session.value.success) {
            viewModelScope.launch(Dispatchers.IO) {
                val _account = repository.getAccount(session.value.session_id)
                if (_account.body() != null) {
                    account.value = _account.body()!!
                    Log.d("TAG", "getRequestToken: $account")
                }
            }
        } else {
            Log.d("TAG", "getAccount: no valid sessionid")
        }
        return account.value
    }

    fun getMovieWatchlist(): List<Result> {
        viewModelScope.launch(Dispatchers.IO) {
            val _movieWatchlist = repository.getMovieWatchlist(account.value.id, session.value.session_id)
            if (_movieWatchlist.isSuccessful && _movieWatchlist.body() != null) {
                movieWatchlist.value = _movieWatchlist.body()!!
            }
        }
        return movieWatchlist.value.results
    }

    fun getTVWatchlist(): List<Result> {
        viewModelScope.launch(Dispatchers.IO) {
            val _tvWatchlist = repository.getTVWatchlist(account.value.id, session.value.session_id)
            if (_tvWatchlist.isSuccessful && _tvWatchlist.body() != null) {
                tvWatchlist.value = _tvWatchlist.body()!!
            }
        }
        return tvWatchlist.value.results
    }
}