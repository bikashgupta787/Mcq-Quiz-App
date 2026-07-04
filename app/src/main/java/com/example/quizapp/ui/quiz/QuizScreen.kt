package com.example.quizapp.ui.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.quizapp.ui.quiz.components.OptionCard
import com.example.quizapp.ui.quiz.components.ProgressHeader
import com.example.quizapp.ui.quiz.components.QuestionCard
import com.example.quizapp.ui.quiz.components.SkipButton
import com.example.quizapp.ui.quiz.components.StreakBadge
import androidx.compose.runtime.*
import com.example.quizapp.navigation.Screen
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith


@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isQuizCompleted) {

        if (uiState.isQuizCompleted) {

            navController.navigate(Screen.Result.route) {

                popUpTo(Screen.Quiz.route) {
                    inclusive = true
                }

            }

        }

    }

    val currentQuestion =
        uiState.questions.getOrNull(uiState.currentQuestionIndex)
            ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {

            ProgressHeader(
                currentQuestion = uiState.currentQuestionIndex + 1,
                totalQuestions = uiState.questions.size
            )

            Spacer(modifier = Modifier.height(16.dp))

            StreakBadge(
                streak = uiState.currentStreak
            )

            Spacer(modifier = Modifier.height(24.dp))


            AnimatedContent(
                targetState = uiState.currentQuestionIndex,
                transitionSpec = {
                    slideInHorizontally { it } + fadeIn() togetherWith
                            slideOutHorizontally { -it } + fadeOut()
                },
                label = ""
            ) {

                val question = uiState.questions[it]

                Column {

                    QuestionCard(
                        question = question.question
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    question.options.forEachIndexed { index, option ->

                        OptionCard(
                            option = option,
                            isSelected = uiState.selectedOptionIndex == index,
                            isAnswerRevealed = uiState.isAnswerRevealed,
                            isCorrect = index == question.correctOptionIndex,
                            onClick = {
                                viewModel.selectAnswer(index)
                            }
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }

                }

            }
        }

        SkipButton(
            modifier = Modifier.navigationBarsPadding(),
            onClick = {
                viewModel.skipQuestion()
            }
        )
    }
}