package com.example.chatwiseuklimitedtask.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwiseuklimitedtask.models.Image
import com.example.chatwiseuklimitedtask.repo.ApiRepoImpl
import com.example.chatwiseuklimitedtask.utils.Event
import com.example.chatwiseuklimitedtask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ApiRepoImpl
) : ViewModel() {
    private val _imagesState =
        MutableStateFlow<Event<Resource<Image>>>(Event(Resource.Init()))
    val imagesState: MutableStateFlow<Event<Resource<Image>>> =
        _imagesState

    fun getAllImages() {
        viewModelScope.launch(Dispatchers.Main) {
            _imagesState.emit(Event(Resource.Loading()))
            val result = repository.getAllImages()
            _imagesState.emit(Event(result))
        }
    }
}