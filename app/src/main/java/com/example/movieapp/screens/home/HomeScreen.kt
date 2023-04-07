package com.example.movieapp.screens.home


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.MovieApp
import com.example.movieapp.R
import com.example.movieapp.data.remote.Trending
import com.example.movieapp.data.remote.Result
import com.example.movieapp.widgets.MoviePreview

@Composable
fun HomeScreen(
    resultsMovieWeek: List<Result>?,
    resultsMovieDay: List<Result>?,
    resultsTvWeek: List<Result>?,
    resultsTvDay: List<Result>?
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.primaryVariant,
                            MaterialTheme.colors.primary
                        )
                    )
                )
        )
        LazyColumn(contentPadding = PaddingValues(bottom = 50.dp)) {
            item {
                TrendingRow(results = resultsMovieWeek, headline = "Trending Movies this week")
                TrendingRow(results = resultsMovieDay, headline = "Trending Movies today")
                TrendingRow(results = resultsTvWeek, headline = "Trending TV shows this week")
                TrendingRow(results = resultsTvDay, headline = "Trending TV shows today")
            }
        }
    }
}

@Composable
fun TrendingRow(results: List<Result>?, headline: String) {
    Text(text = headline, style = MaterialTheme.typography.h5, modifier = Modifier.padding(5.dp))
    if (results != null) {
        LazyRow(
            modifier = Modifier
                .height(350.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colors.primary,
                            Color.Transparent
                        ),
                        startY = 340f
                    )
                )
        ) {
            items(results) {
                MoviePreview(it)
            }
        }
    }
}