package com.example.quizapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.ResultScreen
import com.example.quizapp.ui.SplashScreen
import com.example.quizapp.ui.quiz.QuizScreen
import com.example.quizapp.ui.quiz.QuizViewModel

@Composable
fun QuizNavGraph() {

    val navController = rememberNavController()

    val quizViewModel: QuizViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {

            SplashScreen(
                navController = navController,
                viewModel = quizViewModel
            )
        }

        composable(Screen.Quiz.route) {

            QuizScreen(
                navController = navController,
                viewModel = quizViewModel
            )
        }

        composable(Screen.Result.route) {

            ResultScreen(
                navController = navController,
                viewModel = quizViewModel
            )
        }
    }
}