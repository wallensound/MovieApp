package com.example.movieapp.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.Trending
import com.example.movieapp.data.remote.Result
import com.example.movieapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val repository = Repository()
    private val trending = mutableStateOf(Trending(0, emptyList(), 0, 0))

    fun getTrending(): List<Result>{

        viewModelScope.launch {
            val _trending = repository.getTrending()
            if (_trending.isSuccessful && _trending.body() != null) {
                trending.value = _trending.body()!!
            }
        }
        return trending.value.results
    }

}
