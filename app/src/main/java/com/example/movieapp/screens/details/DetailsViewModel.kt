package com.example.movieapp.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.Result
import com.example.movieapp.data.remote.getmovie.Credits
import com.example.movieapp.data.remote.getmovie.Movie
import com.example.movieapp.data.remote.getmovie.Similar
import com.example.movieapp.data.remote.gettv.CreditsTV
import com.example.movieapp.data.remote.gettv.SimilarTV
import com.example.movieapp.data.remote.gettv.TV
import com.example.movieapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val repository = Repository()

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

}