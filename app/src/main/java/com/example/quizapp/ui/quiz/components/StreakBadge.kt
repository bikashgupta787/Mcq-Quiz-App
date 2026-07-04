package com.example.quizapp.ui.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
fun StreakBadge(
    streak: Int
) {

    val background = if (streak >= 3)
        Color(0xFFFF9800)
    else
        MaterialTheme.colorScheme.surfaceVariant

    val textColor = if (streak >= 3) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }


    Text(
        text = "🔥 Streak: $streak",
        color = textColor,
        modifier = Modifier
            .background(
                background,
                RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    )
}