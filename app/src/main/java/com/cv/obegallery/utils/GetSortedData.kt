package com.cv.obegallery.utils

import com.cv.obegallery.models.NasaData

class GetSortedData(private val nasaData: ArrayList<NasaData>) {
    operator fun invoke(): List<NasaData> {
        return nasaData.sortedByDescending { it.date }
    }
}