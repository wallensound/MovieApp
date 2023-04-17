package com.example.movieapp.screens.account

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.navigation.Screen
import com.example.movieapp.widgets.MoviePreview

@Composable
fun AccountScreen(accountViewModel: AccountViewModel = viewModel(), navController: NavController) {
    val ctx = LocalContext.current
    val requestToken = accountViewModel.getRequestToken()
    val account = accountViewModel.getAccount()
    Column() {
        Row() {
            Button(onClick = {
                if (requestToken.success) {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.themoviedb.org/authenticate/" + requestToken.request_token)
                    )
                    ctx.startActivity(urlIntent)
                } else {
                    Log.d("TAG", "AccountScreen: didntwork")
                }
            }) {
                Text(text = "Validate")
            }
            Button(onClick = { accountViewModel.postCreateSession() }) {
                Text(text = "Start session")
            }
        }
        if (accountViewModel.psession.value.success) {
            val movieResults = accountViewModel.getMovieWatchlist()
            Text(text = account.username)
            Log.d("TAG", "AccountScreen: $movieResults")
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
        }
    }
}