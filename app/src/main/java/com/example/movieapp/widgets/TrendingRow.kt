package com.example.movieapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.data.remote.Result
import com.example.movieapp.navigation.Screen

@Composable
fun TrendingRow(results: List<Result>, headline: String, navController: NavController, movie: Boolean) {
    Text(text = headline, style = MaterialTheme.typography.h6, modifier = Modifier.padding(start = 5.dp))
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
                    if (movie) {
                        navController.navigate(Screen.DetailsScreen.route + "/$Id")
                    } else {
                        navController.navigate(Screen.DetailsScreenTV.route + "/$Id" )
                    }
                }
            }
        }
    }
}