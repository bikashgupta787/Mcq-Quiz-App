package com.example.quizapp.ui.quiz.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.*

@Composable
fun ProgressHeader(
    currentQuestion: Int,
    totalQuestions: Int
) {

    //val progress = currentQuestion.toFloat() / totalQuestions

    val progress by animateFloatAsState(
        targetValue = currentQuestion.toFloat() / totalQuestions,
        label = ""
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Question $currentQuestion of $totalQuestions",
            style = MaterialTheme.typography.titleMedium
        )

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth()
        )
    }
}