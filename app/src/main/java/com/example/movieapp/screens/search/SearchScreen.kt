package com.example.movieapp.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.navigation.Screen
import com.example.movieapp.widgets.MoviePreview
import com.example.movieapp.widgets.ToggleButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(searchViewModel: SearchViewModel = koinViewModel(), navController: NavController) {

    val results = searchViewModel.results
    val allState = searchViewModel.allState
    val movieState = searchViewModel.movieState
    val tvState = searchViewModel.tvState

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
        Column {
            Column(modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxWidth()
                .padding(1.dp),) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextField(
                        value = searchViewModel.query.value,
                        onValueChange = {
                            searchViewModel.query.value = it
                        },
                        maxLines = 1,
                        label = { Text(text = "Search", color = Color.White) },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions { searchViewModel.getResult(searchViewModel.query.value.text) }
                    )
                    IconButton(onClick = {
                        searchViewModel.getResult(searchViewModel.query.value.text)
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val space = 38.dp
                    ToggleButton(state = allState.value, text = "all") {
                        searchViewModel.setState("all")
                        searchViewModel.getResult(searchViewModel.query.value.text)
                    }
                    Spacer(modifier = Modifier.width(space))
                    ToggleButton(state = movieState.value, text = "movie") {
                        searchViewModel.setState("movie")
                        searchViewModel.getResult(searchViewModel.query.value.text)
                    }
                    Spacer(modifier = Modifier.width(space))
                    ToggleButton(state = tvState.value, text = "tv") {
                        searchViewModel.setState("tv")
                        searchViewModel.getResult(searchViewModel.query.value.text)
                    }
                }

            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(7.5.dp)
            ) {
                items(results) { result ->
                    Box(modifier = Modifier.padding(7.5.dp)) {
                        MoviePreview(result = result, onItemClick = { Id ->
                            if (result.media_type == "movie") {
                                navController.navigate(Screen.DetailsScreen.route + "/$Id")
                            } else {
                                navController.navigate(Screen.DetailsScreenTV.route + "/$Id")
                            }
                        })
                    }
                }
            }
        }
    }
}