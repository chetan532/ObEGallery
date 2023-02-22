package com.cv.obegallery.retrofit

import com.cv.obegallery.models.SpaceFact
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("nasa-pictures.json")
    suspend fun getPictures() : Response<ArrayList<SpaceFact>>
}