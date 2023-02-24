package com.cv.obegallery.utils

import java.io.InputStreamReader

object Constants {

    fun readFileResource(fileName: String): String {
        val inputStream = Constants::class.java.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}