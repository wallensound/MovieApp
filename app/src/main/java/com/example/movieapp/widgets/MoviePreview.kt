package com.example.movieapp.widgets

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.data.remote.Result

@Composable
fun MoviePreview(result: Result) {
    Box(Modifier.padding(5.dp)) {
        Column(modifier = Modifier.width(150.dp)) {
            Surface(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
            ) {
                Log.d("TAG", "MoviePreview: ${result.poster_path}")
                AsyncImage(model = "https://image.tmdb.org/t/p/w500${result.poster_path}", contentDescription = "${result.title} poster")
            }
            Column(Modifier.padding(top = 20.dp)) {
                Text(text = result.title, style = MaterialTheme.typography.subtitle1)
                Text(text = result.release_date, style = MaterialTheme.typography.caption)
            }
        }
        Box(modifier = Modifier.padding(start = 20.dp, top = 207.dp)) {
            MovieRating(rating = result.vote_average, size = 0.34f)
        }
    }
}