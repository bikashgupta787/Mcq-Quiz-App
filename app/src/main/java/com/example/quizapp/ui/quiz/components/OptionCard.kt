package com.example.quizapp.ui.quiz.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*

@Composable
fun OptionCard(
    option: String,
    isSelected: Boolean,
    isAnswerRevealed: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit
) {

    val targetColor = when {

        !isAnswerRevealed -> MaterialTheme.colorScheme.surface

        isCorrect -> Color(0xFF4CAF50)

        isSelected -> Color(0xFFE53935)

        else -> MaterialTheme.colorScheme.surface

    }

    val containerColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(400),
        label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isAnswerRevealed) {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {

        Text(
            text = option,
            modifier = Modifier.padding(18.dp)
        )
    }
}