package com.example.proj2.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                // Вместо обращения к method и body напрямую, используйте их через уже существующий RequestBuilder
                val request = original.newBuilder()
                    .header("X-Api-Key", "i9ZWNhqrCXqwzcDxr/Vy6Q==CIoueoBQzngyfwoZ")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    val apiService: CelebrityApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CelebrityApiService::class.java)
    }
}