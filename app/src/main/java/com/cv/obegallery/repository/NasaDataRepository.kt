package com.cv.obegallery.repository

import com.cv.obegallery.models.NasaData
import com.cv.obegallery.retrofit.Api
import com.cv.obegallery.retrofit.Result
import javax.inject.Inject

class NasaDataRepository @Inject constructor(private val api: Api) {

    suspend fun getNasaData(): Result<ArrayList<NasaData>> {
        val result = api.getPictures()

        return if (result.isSuccessful) {
            val responseBody = result.body()

            if (responseBody != null) {
                Result.Success(result.body())
            } else {
                Result.Error("API Error")
            }
        } else {
            Result.Error("API Error")
        }
    }
}