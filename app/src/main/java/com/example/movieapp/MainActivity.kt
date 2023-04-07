package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.home.HomeViewModel
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Scaffold(bottomBar = {
                    BottomNavigation(backgroundColor = MaterialTheme.colors.primary, contentColor = Color.White) {
                        BottomNavigationItem(selected = true, onClick = { /*TODO*/ }, icon = {
                            Icon(
                                imageVector = Icons.Default.TrendingUp,
                                contentDescription = "Trending"
                            )
                        })
                        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Trending"
                            )
                        })
                        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Trending"
                            )
                        })
                    }
                }) {
                    MovieApp()
                }
            }
        }
    }
}

@Composable
fun MovieApp(homeViewModel: HomeViewModel = viewModel()) {
    val resultsMovieWeek = homeViewModel.getTrendingMovieWeek()
    val resultsMovieDay = homeViewModel.getTrendingMovieDay()
    val resultsTvWeek = homeViewModel.getTrendingTvWeek()
    val resultsTvDay = homeViewModel.getTrendingTvDay()
    HomeScreen(resultsMovieWeek, resultsMovieDay, resultsTvWeek, resultsTvDay)
}