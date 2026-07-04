package com.example.quizapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.quizapp.navigation.Screen
import com.example.quizapp.ui.quiz.QuizViewModel
import androidx.compose.runtime.*
import androidx.compose.animation.core.animateIntAsState

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: QuizViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val performance = when {

        uiState.correctAnswers >= 9 ->
            "🏆 Excellent"

        uiState.correctAnswers >= 7 ->
            "⭐ Great"

        uiState.correctAnswers >= 5 ->
            "👍 Good"

        else ->
            "📚 Keep Practicing"

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🎉 Quiz Completed",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Score",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                val animatedScore by animateIntAsState(
                    targetValue = uiState.correctAnswers,
                    label = ""
                )

                Text(
                    text = "$animatedScore/${uiState.questions.size}",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = performance,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Skipped Questions: ${uiState.skippedQuestions}"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Longest Streak: 🔥 ${uiState.longestStreak}"
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                viewModel.restartQuiz()

                navController.navigate(Screen.Quiz.route) {

                    popUpTo(Screen.Result.route) {
                        inclusive = true
                    }

                }
            }
        ) {

            Text("Restart Quiz")
        }

    }
}