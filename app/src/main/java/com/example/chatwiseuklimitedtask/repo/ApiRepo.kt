package com.example.chatwiseuklimitedtask.repo

import com.example.chatwiseuklimitedtask.models.Image
import com.example.chatwiseuklimitedtask.utils.Resource


interface ApiRepo {
    suspend fun getAllImages(): Resource<Image>
}