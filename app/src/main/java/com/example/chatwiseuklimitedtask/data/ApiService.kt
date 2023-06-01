package com.example.chatwiseuklimitedtask.data

import com.example.chatwiseuklimitedtask.models.Image
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getAllImages():Image
}