package com.example.collabera_test.model.api

import com.example.collabera_test.model.api.model.Results
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("/api/")
    suspend fun getUsers(@Query("results")results: Int): Results
}