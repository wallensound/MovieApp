package com.example.movieapp.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.widgets.TrendingRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel(), navController: NavController) {

    val resultsMovieWeek = homeViewModel.getTrendingMovieWeek()
    val resultsMovieDay = homeViewModel.getTrendingMovieDay()
    val resultsTvWeek = homeViewModel.getTrendingTvWeek()
    val resultsTvDay = homeViewModel.getTrendingTvDay()
    // TODO val isLoading = homeViewModel.isLoading

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
// TODO Loading
//        if (isLoading.value) {
//            Text(text = "Loading")
//        }
        LazyColumn {
            item {
                TrendingRow(
                    results = resultsMovieWeek,
                    headline = stringResource(R.string.trending_movies_this_week),
                    navController = navController,
                    movie = true
                )
                TrendingRow(
                    results = resultsMovieDay,
                    headline = stringResource(R.string.trending_movies_today),
                    navController = navController,
                    movie = true
                )
                TrendingRow(
                    results = resultsTvWeek,
                    headline = stringResource(R.string.trending_TV_shows_this_week),
                    navController = navController,
                    movie = false
                )
                TrendingRow(
                    results = resultsTvDay,
                    headline = stringResource(R.string.trending_TV_shows_today),
                    navController = navController,
                    movie = false
                )
            }
        }
    }
}