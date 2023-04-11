package com.example.movieapp.screens.details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.getmovie.Credits
import com.example.movieapp.data.remote.getmovie.Movie
import com.example.movieapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val repository = Repository()
    private val movie = mutableStateOf(
        Movie(
            false,
            "",
            "",
            0,
            emptyList(),
            "",
            0,
            "",
            "",
            "",
            "",
            0.0,
            "",
            emptyList(),
            emptyList(),
            "",
            0,
            0,
            emptyList(),
            "",
            "",
            "",
            false,
            0.0,
            0
        )
    )
    private val credits= mutableStateOf(Credits(emptyList(), emptyList(),0))

    fun getMovie(Id: Int): Movie {
        viewModelScope.launch(Dispatchers.IO) {
            val _movie = repository.getMovie(Id)
            Log.d("TAG", "DetailsScreen: $_movie")
            if (_movie.isSuccessful && _movie.body() != null) {
                movie.value = _movie.body()!!
            }
        }
        return movie.value
    }

    fun getMovieCredits(Id: Int): Credits {
        viewModelScope.launch(Dispatchers.IO) {
            val _movieCredits = repository.getMovieCredits(Id)
            Log.d("TAG", "DetailsScreen: $_movieCredits")
            if (_movieCredits.isSuccessful && _movieCredits.body() != null) {
                credits.value = _movieCredits.body()!!
            }
        }
        return credits.value
    }

}