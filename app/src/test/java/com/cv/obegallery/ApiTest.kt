package com.cv.obegallery

import com.cv.obegallery.retrofit.Api
import com.cv.obegallery.utils.Constants
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var api: Api

    @Before
    fun setup() {

        mockWebServer = MockWebServer()
        api = Retrofit.Builder().baseUrl(mockWebServer.url("/")).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(Api::class.java)
    }

    @Test
    fun testGetNasaData() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = api.getPictures()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @Test
    fun testGetNasaData_returnData() = runTest {
        val mockResponse = MockResponse()
        val data = Constants.readFileResource("/data.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(data)
        mockWebServer.enqueue(mockResponse)

        val response = api.getPictures()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.body()!!.isEmpty())
        Assert.assertEquals(26, response.body()!!.size)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}