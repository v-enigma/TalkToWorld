package com.example.talktoworld.network

import com.example.talktoworld.model.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

import retrofit2.http.GET


interface TalkApiService {
       @GET("photos")
       suspend fun getPhotos(): List<MarsPhoto>
}

