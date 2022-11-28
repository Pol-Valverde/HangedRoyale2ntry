package com.example.hangedroyale2ntry

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface HangManInterface
{
    @POST("/hangman")
    fun getHangmanWord(): Call<ApiHangManGame>

    @PUT("/hangman")
    fun checkLetter(@Query("letter") letter:String,@Query("token") token:String): Call<GuessLetterHangMan>

}