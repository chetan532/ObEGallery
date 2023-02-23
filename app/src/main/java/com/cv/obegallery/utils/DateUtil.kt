package com.cv.obegallery.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(currentDate: String?): String? {
    val dateFormat = SimpleDateFormat("dd MMM yyy", Locale.getDefault())
    val currentDateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
    val date = currentDate?.let { currentDateFormat.parse(it) }
    return date?.let { dateFormat.format(it) } ?: currentDate
}