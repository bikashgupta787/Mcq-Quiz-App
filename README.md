# 📱 Android Quiz App

A modern Android Quiz application built using **Kotlin**, **Jetpack Compose**, **MVVM**, and **Material 3**. The app fetches quiz questions from a remote JSON endpoint, provides an engaging quiz experience with streak tracking, and displays detailed results at the end.

## ✨ Features

* 🚀 Fetches quiz questions from a remote API using Retrofit
* 🎨 Built entirely with Jetpack Compose
* 🏗️ MVVM Architecture
* 💉 Dependency Injection using Hilt
* 🌐 Network calls using Retrofit
* 🔄 State management with StateFlow
* ⏳ Splash screen with loading indicator
* ✅ Answer validation with immediate feedback
* ⏭️ Auto advances to the next question after 2 seconds
* ⏩ Skip question functionality
* 🔥 Consecutive correct answer streak tracking
* 🏆 Longest streak calculation
* 📊 Animated quiz progress indicator
* 🎯 Result screen with:

  * Correct Answers
  * Skipped Questions
  * Longest Streak
  * Performance Badge
* 🔁 Restart Quiz functionality
* 🌙 Supports Light & Dark Theme

---

## 🛠 Tech Stack

* Kotlin
* Jetpack Compose
* MVVM Architecture
* Hilt
* Retrofit
* Gson
* Coroutines
* StateFlow
* Navigation Compose
* Material 3

---

## 📂 Project Structure

```
app
├── data
│   ├── model
│   ├── remote
│   └── repository
│
├── di
│
├── navigation
│
├── ui
│   ├── splash
│   ├── quiz
│   │   └── components
│   └── result
│
├── utils
│
└── theme
```

---

## 🏛 Architecture

The project follows the **MVVM (Model–View–ViewModel)** architecture.

```
UI (Compose)
      │
      ▼
ViewModel
      │
      ▼
Repository
      │
      ▼
Retrofit API
      │
      ▼
Remote JSON
```

State is managed using **StateFlow**, ensuring a reactive and unidirectional data flow.

---

## 🚀 How It Works

1. The app launches with a Splash Screen.
2. Quiz questions are fetched from a remote JSON endpoint.
3. Users answer or skip questions.
4. Selecting an answer reveals the correct option.
5. After a short delay, the app automatically moves to the next question.
6. Consecutive correct answers build a streak.
7. At the end of the quiz, a Results screen displays:

   * Total Correct Answers
   * Skipped Questions
   * Longest Streak
   * Performance Rating

---

## 🎯 Future Improvements

* Swipe gesture to skip questions
* Confetti animation for high scores
* Sound effects and haptic feedback
* Difficulty levels
* Countdown timer
* Category-based quizzes
* Offline caching
* Unit tests and UI tests

---

## 👨‍💻 Author

**Bikash Gupta**

Android Developer | Kotlin | Jetpack Compose | MVVM

---

## 📄 License

This project is created for learning and demonstration purposes.
