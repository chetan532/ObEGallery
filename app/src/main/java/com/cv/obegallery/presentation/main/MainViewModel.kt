package com.cv.obegallery.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cv.obegallery.models.NasaData
import com.cv.obegallery.repository.NasaDataRepository
import com.cv.obegallery.retrofit.Result
import javax.inject.Inject
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(private val repository: NasaDataRepository) : ViewModel() {

    private val _nasaData = MutableLiveData<Result<ArrayList<NasaData>>>()

    val nasaDataLiveData: LiveData<Result<ArrayList<NasaData>>>
        get() = _nasaData

    var selectedIndex = 0

    fun getNasaData() {
        viewModelScope.launch {
            val result = repository.getNasaData()
            _nasaData.postValue(result)
        }
    }
}