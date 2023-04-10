package com.example.movieapp.screens.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.movieapp.navigation.Screen

@Composable
fun DetailsScreen(id: Int?) {
    Text(text = id.toString())
}
