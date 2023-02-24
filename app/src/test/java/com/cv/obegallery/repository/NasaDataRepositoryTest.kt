package com.cv.obegallery.repository

import com.cv.obegallery.retrofit.Api
import com.cv.obegallery.retrofit.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class NasaDataRepositoryTest {

    @Mock
    lateinit var api: Api

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetNasaData_EmptyList() = runTest {
        Mockito.`when`(api.getPictures()).thenReturn(Response.success(ArrayList()))

        val sut = NasaDataRepository(api)
        val result = sut.getNasaData()
        Assert.assertEquals(true, result is Result.Success)
        Assert.assertEquals(0, result.data!!.size)
    }

    @Test
    fun testGetNasaData_getDataList() = runTest {
        Mockito.`when`(api.getPictures()).thenReturn(Response.success(ArrayList()))

        val sut = NasaDataRepository(api)
        val result = sut.getNasaData()
        Assert.assertEquals(true, result is Result.Success)
        Assert.assertEquals(0, result.data!!.size)
    }
}