package com.cv.obegallery.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cv.obegallery.models.NasaData
import com.cv.obegallery.repository.NasaDataRepository
import com.cv.obegallery.retrofit.Result
import javax.inject.Inject
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(private val repository: NasaDataRepository) : ViewModel() {

    val nasaDataLiveData: LiveData<Result<ArrayList<NasaData>>>
        get() = repository.nasaData

    fun getNasaData() {
        viewModelScope.launch {
            repository.getNasaData()
        }
    }
}