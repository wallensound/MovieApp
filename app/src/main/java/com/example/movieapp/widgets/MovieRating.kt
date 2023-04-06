package com.example.movieapp.widgets

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieRating(rating: Double, size: Float) {
    val percent = (rating * 10).toInt()

    val color = when (percent) {
        in 70..100 -> Color.Green
        in 50..69 -> Color.Yellow
        else -> Color.Red
    }

    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercetage = animateFloatAsState(
        targetValue = if (animationPlayed) (rating/10).toFloat() else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(contentAlignment = Alignment.Center,
    modifier = Modifier.size(100.dp * size)) {
        Canvas(modifier = Modifier.size(100.dp * size)) {
            drawCircle(
                color = Color.DarkGray,
                radius = 180f * size
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke((8.dp * size).toPx(), cap = StrokeCap.Round),
                alpha = 0.5f
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercetage.value,
                useCenter = false,
                style = Stroke((8.dp * size).toPx(), cap = StrokeCap.Round)
            )
        }
        Text(text = percent.toString(), color = Color.White, fontSize = 50.sp * size, fontWeight = FontWeight.Bold)
    }
}