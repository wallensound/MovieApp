package com.example.movieapp.screens.details

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.data.remote.getaccount.PostAddToWatchlist
import com.example.movieapp.widgets.MovieRating
import com.example.movieapp.widgets.TrendingRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel = koinViewModel(),
    navController: NavController,
    id: Int?
) {

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

        val accountStates = detailsViewModel.getMovieAccountStates(id)
        val movie = detailsViewModel.getMovie(id)
        val credits = detailsViewModel.getMovieCredits(id)
        val similar = detailsViewModel.getMovieSimilar(id)

        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.height(100.dp),
                contentScale = ContentScale.FillWidth,
                model = "https://image.tmdb.org/t/p/original${movie.backdrop_path}",
                contentDescription = "${movie.title} poster",
            )
            LazyColumn {
                item {
                    Column(modifier = Modifier.padding(top = 20.dp, start = 5.dp, end = 5.dp)) {
                        Text(
                            text = "${movie.title} (${movie.release_date.dropLast(6)})",
                            style = MaterialTheme.typography.h5,
                            color = Color.White
                        )
                        Row {
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
                    }
                    Column(modifier = Modifier.padding(top = 20.dp, start = 5.dp, end = 5.dp)) {
                        Text(
                            text = stringResource(R.string.summary),
                            style = MaterialTheme.typography.h6,
                            color = Color.White
                        )
                        Text(
                            text = movie.overview,
                            style = MaterialTheme.typography.body1,
                            color = Color.White
                        )
                    }
                    Text(
                        text = stringResource(R.string.actors),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(top = 20.dp, start = 5.dp),
                        color = Color.White
                    )
                    LazyRow(
                        modifier = Modifier
                            .height(310.dp)
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
                        items(credits.cast) {
                            Box(Modifier.padding(5.dp)) {
                                Column(modifier = Modifier.width(150.dp)) {
                                    Surface(
                                        modifier = Modifier
                                            .clip(shape = RoundedCornerShape(16.dp))
                                            .height(225.dp)
                                    ) {
                                        AsyncImage(
                                            model = "https://image.tmdb.org/t/p/w500${it.profile_path}",
                                            contentDescription = "${movie.title} poster",
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                    Column(Modifier.padding(top = 5.dp)) {
                                        Text(
                                            text = it.name,
                                            style = MaterialTheme.typography.subtitle1,
                                            color = Color.White
                                        )
                                        Text(
                                            text = it.character,
                                            style = MaterialTheme.typography.caption,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                    TrendingRow(
                        results = similar,
                        headline = stringResource(R.string.similar),
                        navController = navController,
                        movie = true
                    )
                }

            }
        }
        Box(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
                .height(10.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0f, 0f, 0f, 0.7f),
                            Color.Transparent
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp, start = 10.dp, end = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            MovieRating(rating = movie.vote_average, size = 0.4f)
            Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    if (accountStates) {
                        //TODO create a error message to be displayed on the screen
                        Log.d("TAG", "DetailsScreen: already added")
                    } else {
                        detailsViewModel.postAddToWatchlist(
                            PostAddToWatchlist(
                                media_id = id,
                                media_type = "movie"
                            )
                        )
                    }
                }, shape = CircleShape) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = stringResource(R.string.add_to_watchlist),
                        tint = if (accountStates) Color.Red else Color.White
                    )
                }
            }
        }
    }
}

