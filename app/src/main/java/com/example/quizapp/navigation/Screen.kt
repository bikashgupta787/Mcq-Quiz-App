package com.example.quizapp.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash")

    data object Quiz : Screen("quiz")

    data object Result : Screen("result")
}