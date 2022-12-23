package com.example.hangedroyale2ntry.dataClasses

data class GuessLetterHangMan(
    val token: String,
    val letter: String,
    val correct: Boolean,
    val hangman: String
)
