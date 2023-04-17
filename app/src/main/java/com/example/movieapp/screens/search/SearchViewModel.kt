package com.example.movieapp.screens.search

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.getsearch.Result
import com.example.movieapp.data.remote.getsearch.Search
import com.example.movieapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val repository = Repository()
    private val search = mutableStateOf(Search(emptyList()))
    val query = mutableStateOf(TextFieldValue(""))
    private val _results = mutableStateListOf<Result>()
    var results: List<Result> = _results
    val allState = mutableStateOf(true)
    val movieState = mutableStateOf(false)
    val tvState = mutableStateOf(false)

    fun setState(filterBy: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (filterBy == "movie") {
                allState.value = false
                movieState.value = true
                tvState.value = false
            } else if (filterBy == "tv") {
                allState.value = false
                movieState.value = false
                tvState.value = true
            } else {
                allState.value = true
                movieState.value = false
                tvState.value = false
            }

        }
    }

    fun getResult(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getSearch(query)
            if (result.isSuccessful && result.body() != null) {
                search.value = result.body()!!
            }
            _results.clear()
            _results.addAll(search.value.results.filter {
                if (movieState.value) {
                    it.media_type == "movie"
                } else if (tvState.value) {
                    it.media_type == "tv"
                } else {
                    it.media_type == "movie" || it.media_type == "tv"
                }
            })
        }
    }
}