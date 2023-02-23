package com.cv.obegallery

import android.app.Application
import com.cv.obegallery.di.ApplicationComponent
import com.cv.obegallery.di.DaggerApplicationComponent

class MainApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}