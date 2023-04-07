package com.example.movieapp.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.Trending
import com.example.movieapp.data.remote.Result
import com.example.movieapp.data.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = Repository()
    private val trendingMovieWeek = mutableStateOf(Trending(0, emptyList(), 0, 0))
    private val trendingMovieDay = mutableStateOf(Trending(0, emptyList(), 0, 0))
    private val trendingTvWeek = mutableStateOf(Trending(0, emptyList(), 0, 0))
    private val trendingTvDay = mutableStateOf(Trending(0, emptyList(), 0, 0))

    fun getTrendingMovieWeek(): List<Result>{

        viewModelScope.launch {
            val _trendingMovieWeek = repository.getTrendingMovieWeek()
            if (_trendingMovieWeek.isSuccessful && _trendingMovieWeek.body() != null) {
                trendingMovieWeek.value = _trendingMovieWeek.body()!!
            }
        }
        return trendingMovieWeek.value.results
    }

    fun getTrendingMovieDay(): List<Result>{

        viewModelScope.launch {
            val _trendingMovieDay = repository.getTrendingMovieDay()
            if (_trendingMovieDay.isSuccessful && _trendingMovieDay.body() != null) {
                trendingMovieDay.value = _trendingMovieDay.body()!!
            }
        }
        return trendingMovieDay.value.results
    }

    fun getTrendingTvWeek(): List<Result>{

        viewModelScope.launch {
            val _trendingTvWeek = repository.getTrendingTvWeek()
            if (_trendingTvWeek.isSuccessful && _trendingTvWeek.body() != null) {
                trendingTvWeek.value = _trendingTvWeek.body()!!
            }
        }
        return trendingTvWeek.value.results
    }

    fun getTrendingTvDay(): List<Result>{

        viewModelScope.launch {
            val _trendingTvDay = repository.getTrendingTvDay()
            if (_trendingTvDay.isSuccessful && _trendingTvDay.body() != null) {
                trendingTvDay.value = _trendingTvDay.body()!!
            }
        }
        return trendingTvDay.value.results
    }

}
