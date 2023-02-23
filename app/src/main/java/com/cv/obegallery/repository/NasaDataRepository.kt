package com.cv.obegallery.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cv.obegallery.models.NasaData
import com.cv.obegallery.retrofit.Api
import javax.inject.Inject

class NasaDataRepository @Inject constructor(private val api: Api) {

    private val _nasaData = MutableLiveData<ArrayList<NasaData>>()
    val nasaData: LiveData<ArrayList<NasaData>>
        get() = _nasaData

    suspend fun getNasaData() {
        val result = api.getPictures()

        if (result.isSuccessful && result.body() != null) {
            _nasaData.postValue(result.body())
        }
    }
}