package com.cv.obegallery.di

import com.cv.obegallery.retrofit.Api
import com.cv.obegallery.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val client = OkHttpClient.Builder()
        val clientBuilder = client.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(clientBuilder)
            .build()
    }

    @Singleton
    @Provides
    fun providesMoviesApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}