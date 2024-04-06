package com.example.proj2.network

import com.example.proj2.model.Celebrity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CelebrityApiService {
    @GET("celebrity")
    fun searchCelebrities(@Query("name") name: String): Call<List<Celebrity>>
}