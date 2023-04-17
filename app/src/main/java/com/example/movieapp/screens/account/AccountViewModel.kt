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
import com.example.movieapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {
    private val repository = Repository()
    private val requestToken = mutableStateOf(RequestToken("","",false))
    private val session = mutableStateOf(Session("", false))
    val psession = session
    private val account = mutableStateOf(Account(0,""))
    private val movieWatchlist = mutableStateOf(MovieWatchList(emptyList()))
    private val tvWatchlist = mutableStateOf(MovieWatchList(emptyList()))
    fun getRequestToken(): RequestToken {
        viewModelScope.launch(Dispatchers.IO) {
            val _requestToken = repository.getRequestToken()
            if (_requestToken.body() != null) {
                requestToken.value = _requestToken.body()!!
                Log.d("TAG", "getRequestToken: $requestToken")
            }
        }
        return requestToken.value
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
}