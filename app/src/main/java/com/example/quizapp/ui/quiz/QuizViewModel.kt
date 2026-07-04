package com.example.quizapp.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState = _uiState.asStateFlow()

    fun loadQuestions() {

        if (_uiState.value.questions.isNotEmpty()) return

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            when (val result = repository.getQuestions()) {

                is Resource.Success -> {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            questions = result.data
                        )
                    }

                }

                is Resource.Error -> {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }

                }

                Resource.Loading -> Unit
            }
        }
    }

    fun selectAnswer(selectedIndex: Int) {

        if (_uiState.value.isAnswerRevealed) return

        val currentQuestion =
            _uiState.value.questions[_uiState.value.currentQuestionIndex]

        val isCorrect =
            selectedIndex == currentQuestion.correctOptionIndex

        var streak =
            _uiState.value.currentStreak

        var longestStreak =
            _uiState.value.longestStreak

        var correctAnswers =
            _uiState.value.correctAnswers

        if (isCorrect) {

            streak++
            correctAnswers++

            if (streak > longestStreak) {
                longestStreak = streak
            }

        } else {

            streak = 0

        }

        _uiState.update {

            it.copy(
                selectedOptionIndex = selectedIndex,
                isAnswerRevealed = true,
                correctAnswers = correctAnswers,
                currentStreak = streak,
                longestStreak = longestStreak
            )

        }

        viewModelScope.launch {

            delay(2000)

            moveToNextQuestion()

        }
    }

    fun skipQuestion() {

        if (_uiState.value.isAnswerRevealed) return

        _uiState.update {

            it.copy(
                skippedQuestions = it.skippedQuestions + 1,
                currentStreak = 0
            )

        }

        moveToNextQuestion()
    }

    private fun moveToNextQuestion() {

        val nextIndex =
            _uiState.value.currentQuestionIndex + 1

        if (nextIndex >= _uiState.value.questions.size) {

            _uiState.update {

                it.copy(
                    isQuizCompleted = true
                )

            }

            return

        }

        _uiState.update {

            it.copy(
                currentQuestionIndex = nextIndex,
                selectedOptionIndex = -1,
                isAnswerRevealed = false
            )

        }
    }

    fun restartQuiz() {

        _uiState.update {

            it.copy(
                currentQuestionIndex = 0,
                selectedOptionIndex = -1,
                isAnswerRevealed = false,
                correctAnswers = 0,
                skippedQuestions = 0,
                currentStreak = 0,
                longestStreak = 0,
                isQuizCompleted = false
            )

        }

    }

}