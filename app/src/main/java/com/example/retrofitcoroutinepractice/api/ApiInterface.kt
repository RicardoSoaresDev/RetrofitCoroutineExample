package com.example.retrofitcoroutinepractice.api

import com.example.retrofitcoroutinepractice.model.ResponseListUsers
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api/users?page=2")
    suspend fun getAllUsers(): Response<ResponseListUsers>
}