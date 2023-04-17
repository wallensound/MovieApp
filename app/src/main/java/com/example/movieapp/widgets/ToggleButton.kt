package com.example.movieapp.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToggleButton(state: Boolean, text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (state) {
                MaterialTheme.colors.primaryVariant
            } else {
                MaterialTheme.colors.primary
            }
        ),
        border = BorderStroke(1.dp, color = MaterialTheme.colors.primaryVariant)

    ) {
        Text(text = text, color = Color.White)
    }
}