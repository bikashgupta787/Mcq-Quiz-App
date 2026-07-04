package com.example.quizapp.ui.quiz

import com.example.quizapp.data.Question

data class QuizUiState(

    val isLoading: Boolean = false,

    val questions: List<Question> = emptyList(),

    val currentQuestionIndex: Int = 0,

    val selectedOptionIndex: Int? = null,

    val isAnswerRevealed: Boolean = false,

    val correctAnswers: Int = 0,

    val skippedQuestions: Int = 0,

    val currentStreak: Int = 0,

    val longestStreak: Int = 0,

    val error: String? = null,

    val isQuizCompleted: Boolean = false
)