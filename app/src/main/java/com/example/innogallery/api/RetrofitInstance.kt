package com.example.innogallery.api

import com.example.innogallery.util.Constants.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        val client :OkHttpClient = OkHttpClient.Builder().build()
        val gson : Gson = GsonBuilder().create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        }


    val api by lazy {
        retrofit.create(GalleryAPI::class.java)
    }
}