package com.example.movieapp.screens.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.MovieApp
import com.example.movieapp.data.remote.Trending
import com.example.movieapp.data.remote.Result
import com.example.movieapp.widgets.MoviePreview

@Composable
fun HomeScreen(results: List<Result>?) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column() {
             Text(text = "Trending", style = MaterialTheme.typography.h5)
            if (results != null) {
                LazyRow() {
                    items(results) {
                            MoviePreview(it)
                    }
                }
            }
        }
    }
}