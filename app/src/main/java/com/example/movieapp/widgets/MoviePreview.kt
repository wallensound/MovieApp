package com.example.movieapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.data.remote.Result

@Composable
fun MoviePreview(result: Result, onItemClick: (Int) -> Unit) {
    Box(Modifier.padding(5.dp)) {
        Column(modifier = Modifier.width(150.dp)) {
            Surface(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .clickable { onItemClick(result.id) }
                    .height(225.dp)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${result.poster_path}",
                    contentDescription = "${result.title} poster",
                    contentScale = ContentScale.Crop
                )
            }
            Column(Modifier.padding(top = 20.dp)) {
                Text(
                    text = result.title ?: result.name,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = result.release_date ?: result.first_air_date,
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Box(modifier = Modifier.padding(start = 20.dp, top = 207.dp)) {
            MovieRating(rating = result.vote_average, size = 0.34f)
        }
    }
}

@Composable
fun MoviePreview(result: com.example.movieapp.data.remote.getsearch.Result, onItemClick: (Int) -> Unit) {
        Column(modifier = Modifier.width(150.dp)) {
            Surface(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .clickable { onItemClick(result.id) }
                    .height(225.dp)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${result.poster_path}",
                    contentDescription = "${result.title} poster",
                    contentScale = ContentScale.Crop
                )
            }
            Column(Modifier.padding(top = 20.dp)) {
                Text(
                    text = result.title ?: result.name,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = result.release_date ?: result.first_air_date,
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Box(modifier = Modifier.padding(start = 20.dp, top = 207.dp)) {
            MovieRating(rating = result.vote_average, size = 0.34f)
        }
}