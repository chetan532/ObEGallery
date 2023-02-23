package com.cv.obegallery.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cv.obegallery.repository.NasaDataRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: NasaDataRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}