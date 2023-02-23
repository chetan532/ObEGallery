package com.cv.obegallery.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cv.obegallery.models.NasaData
import com.cv.obegallery.retrofit.Api
import com.cv.obegallery.retrofit.Result
import javax.inject.Inject

class NasaDataRepository @Inject constructor(private val api: Api) {

    private val _nasaData = MutableLiveData<Result<ArrayList<NasaData>>>()
    val nasaData: LiveData<Result<ArrayList<NasaData>>>
        get() = _nasaData

    suspend fun getNasaData() {

        try {
            val result = api.getPictures()

            if (result.isSuccessful && result.body() != null) {
                _nasaData.postValue(Result.Success(result.body()))
            } else {
                _nasaData.postValue(Result.Error("API Error"))
            }
        } catch (e: Exception) {
            _nasaData.postValue(Result.Error(e.message.toString()))
        }
    }
}