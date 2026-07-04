package com.example.quizapp.repository

import com.example.quizapp.data.Question
import com.example.quizapp.data.QuizApi
import com.example.quizapp.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val quizApi: QuizApi
) {

    suspend fun getQuestions(): Resource<List<Question>> {

        return try {

            val questions = quizApi.getQuestions()

            Resource.Success(questions)

        } catch (e: IOException) {

            Resource.Error("Please check your internet connection.")

        } catch (e: HttpException) {

            Resource.Error("Something went wrong. Please try again.")

        } catch (e: Exception) {

            Resource.Error("Unexpected error occurred.")
        }
    }
}