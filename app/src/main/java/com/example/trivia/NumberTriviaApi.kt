package com.example.trivia

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberTriviaApi {
    @GET("{number}")
    fun getTriviaForNumber(@Path("number") number: Int): Call<NumberTrivia>
}