package com.example.chatwiseuklimitedtask.repo


import com.example.chatwiseuklimitedtask.data.ApiService
import com.example.chatwiseuklimitedtask.models.Image
import com.example.chatwiseuklimitedtask.utils.Resource
import com.example.chatwiseuklimitedtask.utils.Utils.tryCatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepoImpl
@Inject constructor(private val apiService: ApiService) : ApiRepo {
    override suspend fun getAllImages(): Resource<Image> =
        withContext(Dispatchers.IO) {
            tryCatch {
                val result = apiService.getAllImages()
                Resource.Success(result)
            }
        }
}