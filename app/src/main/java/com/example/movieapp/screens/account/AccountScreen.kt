package com.example.movieapp.screens.account

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.navigation.Screen
import com.example.movieapp.widgets.MoviePreview
import com.example.movieapp.widgets.ToggleButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen(
    accountViewModel: AccountViewModel = koinViewModel(),
    navController: NavController
) {

    val sessionId = accountViewModel.sessionIdFlow.collectAsState(initial = "null")

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
            if (sessionId.value == "null") {
                Text(text = "Session ID: ${sessionId.value}")
                Login(accountViewModel)
            } else {
                Account(accountViewModel, navController)
            }
        }
    }
}

@Composable
fun Login(accountViewModel: AccountViewModel) {

    val ctx = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            accountViewModel.getRequestToken()
        }) {
            Text(text = "Request", color = Color.White)
        }
        Button(onClick = {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.themoviedb.org/authenticate/" + accountViewModel.requestToken.value.request_token)
            )
            ctx.startActivity(urlIntent)
        }) {
            Text(text = "Validate", color = Color.White)
        }
        Button(onClick = { accountViewModel.postCreateSession() }) {
            Text(text = "Start session", color = Color.White)
        }
    }
}

@Composable
fun Account(accountViewModel: AccountViewModel, navController: NavController) {
    val account = accountViewModel.getAccount()
    val movieResults = accountViewModel.getMovieWatchlist()
    val tvResults = accountViewModel.getTVWatchlist()
    val filterState = accountViewModel.filterState
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 1.dp),
    ) {
        Row {
            Text(text = "User: ", style = MaterialTheme.typography.h6, color = Color.White)
            Text(text = account.username, style = MaterialTheme.typography.h6, color = Color.White)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            ToggleButton(
                state = filterState.value,
                text = "movie",
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                accountViewModel.setState()
            }
            ToggleButton(
                state = !filterState.value,
                text = "tv",
                modifier = Modifier.fillMaxWidth()
            ) {
                accountViewModel.setState()
            }
        }
    }
    if (filterState.value) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(7.5.dp)
        ) {
            items(movieResults) { result ->
                Box(modifier = Modifier.padding(7.5.dp)) {
                    MoviePreview(result = result, onItemClick = { Id ->
                        navController.navigate(Screen.DetailsScreen.route + "/$Id")
                    })
                }
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(7.5.dp)
        ) {
            items(tvResults) { result ->
                Box(modifier = Modifier.padding(7.5.dp)) {
                    MoviePreview(result = result, onItemClick = { Id ->
                        navController.navigate(Screen.DetailsScreenTV.route + "/$Id")
                    })
                }
            }
        }
    }
}