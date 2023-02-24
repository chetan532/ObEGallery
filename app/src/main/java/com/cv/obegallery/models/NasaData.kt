package com.cv.obegallery.models

import android.os.Parcel
import android.os.Parcelable

data class NasaData(
    val copyright: String = "",
    val date: String = "",
    val explanation: String = "",
    val hdurl: String = "",
    val media_type: String = "",
    val service_version: String = "",
    val title: String = "",
    val url: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(copyright)
        parcel.writeString(date)
        parcel.writeString(explanation)
        parcel.writeString(hdurl)
        parcel.writeString(media_type)
        parcel.writeString(service_version)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NasaData> {
        override fun createFromParcel(parcel: Parcel): NasaData {
            return NasaData(parcel)
        }

        override fun newArray(size: Int): Array<NasaData?> {
            return arrayOfNulls(size)
        }
    }
}