package com.example.movieapp.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.Result
import com.example.movieapp.data.remote.Trending
import com.example.movieapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val trendingMovieWeek = mutableStateOf(Trending(emptyList()))
    private val trendingMovieDay = mutableStateOf(Trending(emptyList()))
    private val trendingTvWeek = mutableStateOf(Trending(emptyList()))
    private val trendingTvDay = mutableStateOf(Trending(emptyList()))
    //TODO val isLoading = mutableStateOf(false)

    fun getTrendingMovieWeek(): List<Result>{
        //TODO laddindikator
        //isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val trendingMovieWeekResponse = repository.getTrendingMovieWeek()
            if (trendingMovieWeekResponse.isSuccessful && trendingMovieWeekResponse.body() != null) {
                trendingMovieWeek.value = trendingMovieWeekResponse.body()!!
            } else {
                //TODO det sket sig, pröva igen. Kanske en knapp som försöker hämta listan igen
            }
            //TODO ta bort laddindikator
            //isLoading.value = false
        }
        return trendingMovieWeek.value.results
    }

    fun getTrendingMovieDay(): List<Result>{

        viewModelScope.launch(Dispatchers.IO) {
            val trendingMovieDayResponse = repository.getTrendingMovieDay()
            if (trendingMovieDayResponse.isSuccessful && trendingMovieDayResponse.body() != null) {
                trendingMovieDay.value = trendingMovieDayResponse.body()!!
            }
        }
        return trendingMovieDay.value.results
    }

    fun getTrendingTvWeek(): List<Result>{

        viewModelScope.launch(Dispatchers.IO) {
            val trendingTvWeekResponse = repository.getTrendingTvWeek()
            if (trendingTvWeekResponse.isSuccessful && trendingTvWeekResponse.body() != null) {
                trendingTvWeek.value = trendingTvWeekResponse.body()!!
            }
        }
        return trendingTvWeek.value.results
    }

    fun getTrendingTvDay(): List<Result>{

        viewModelScope.launch(Dispatchers.IO) {
            val trendingTvDayResponse = repository.getTrendingTvDay()
            if (trendingTvDayResponse.isSuccessful && trendingTvDayResponse.body() != null) {
                trendingTvDay.value = trendingTvDayResponse.body()!!
            }
        }
        return trendingTvDay.value.results
    }

}
