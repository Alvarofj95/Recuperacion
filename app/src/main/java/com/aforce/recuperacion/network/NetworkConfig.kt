package com.aforce.recuperacion.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {
    private val logging = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build();

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.43:1111/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(Service::class.java)
}