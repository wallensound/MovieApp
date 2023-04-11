package com.example.movieapp.screens.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movieapp.navigation.Screen
import com.example.movieapp.widgets.MovieRating

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel = viewModel(), id: Int?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.secondary,
                        MaterialTheme.colors.primary
                    )
                )
            )
    )
    if (id == null) {
        Text(text = "movieId == null")
    } else {

        val movie = detailsViewModel.getMovie(id)
        val credits = detailsViewModel.getMovieCredits(id)

        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                contentScale = ContentScale.FillWidth,
                model = "https://image.tmdb.org/t/p/original${movie.backdrop_path}",
                contentDescription = "${movie.title} poster",
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black,
                                Color.Transparent
                            )
                        )
                    )
            )
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            ) {
                item {
                    Text(
                        text = "${movie.title} (${movie.release_date.dropLast(6)})",
                        style = MaterialTheme.typography.h5,
                        maxLines = 1
                    )
                    Row() {
                        Text(
                            text = "${movie.release_date}, ",
                            style = MaterialTheme.typography.caption,
                            color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.4f)
                        )
                        movie.genres.forEach { genre ->
                            Text(
                                text = "${genre.name}, ",
                                style = MaterialTheme.typography.caption,
                                color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.4f)
                            )
                        }
                        Text(
                            text = "${movie.runtime / 60}h ${movie.runtime % 60}m",
                            style = MaterialTheme.typography.caption,
                            color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.4f)
                        )
                    }
                    Row(
                        Modifier.padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MovieRating(rating = movie.vote_average, size = 0.5f)
                        Text(
                            text = "User Rating",
                            Modifier.padding(start = 10.dp),
                            style = MaterialTheme.typography.h6
                        )
                        Button(onClick = { /*TODO*/ }, shape = CircleShape) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Add to Watchlist",
                                tint = Color.White
                            )
                        }
                    }
                    Text(text = "Summary", style = MaterialTheme.typography.h6)
                    Text(text = movie.overview, style = MaterialTheme.typography.body1)
                    Text(text = "Actors", style = MaterialTheme.typography.h6)
                    LazyRow(contentPadding = PaddingValues(bottom = 100.dp)) {
                        items(credits.cast) {
                            Card() {
                                Column(modifier = Modifier.size(150.dp,250.dp)) {
                                    AsyncImage(
                                        modifier = Modifier.height(150.dp),
                                        contentScale = ContentScale.Crop,
                                        model = "https://image.tmdb.org/t/p/original${it.profile_path}",
                                        contentDescription = "${movie.title} poster",
                                    )
                                    Text(text = it.name)
                                    Text(text = it.character)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}
