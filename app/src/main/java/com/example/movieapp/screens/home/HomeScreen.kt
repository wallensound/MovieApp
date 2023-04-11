package com.example.movieapp.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.data.remote.Result
import com.example.movieapp.navigation.Screen
import com.example.movieapp.widgets.MoviePreview

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(), navController: NavController) {
    val resultsMovieWeek = homeViewModel.getTrendingMovieWeek()
    val resultsMovieDay = homeViewModel.getTrendingMovieDay()
    val resultsTvWeek = homeViewModel.getTrendingTvWeek()
    val resultsTvDay = homeViewModel.getTrendingTvDay()
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
                TrendingRow(results = resultsMovieWeek, headline = stringResource(R.string.trending_movies_this_week), navController = navController)
                //Todo change to resource, no text in code
                TrendingRow(results = resultsMovieDay, headline = "Trending movies today", navController = navController)
                TrendingRow(results = resultsTvWeek, headline = "Trending TV shows this week", navController = navController)
                TrendingRow(results = resultsTvDay, headline = "Trending TV shows today", navController = navController)
            }
        }
    }
}

@Composable
private fun TrendingRow(results: List<Result>?, headline: String, navController: NavController) {
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
                MoviePreview(it) {Id ->
                    navController.navigate(Screen.DetailsScreen.route+"/$Id")
                }
            }
        }
    }
}