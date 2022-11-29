package com.example.hangedroyale2ntry

data class GuessLetterHangMan(
    val token: String,
    val letter: String,
    val correct: Boolean,
    val hangman: String
)
