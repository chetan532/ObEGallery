package com.cv.obegallery.presentation.bottomsheet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cv.obegallery.models.NasaData
import com.cv.obegallery.utils.Constants

class BottomSheetViewModel constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val nasaData = savedStateHandle.getLiveData<NasaData>(Constants.NASA_DATA_KEY)
}