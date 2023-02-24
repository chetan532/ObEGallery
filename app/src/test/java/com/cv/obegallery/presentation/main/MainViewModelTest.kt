package com.cv.obegallery.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cv.obegallery.getOrAwaitValue
import com.cv.obegallery.repository.NasaDataRepository
import com.cv.obegallery.retrofit.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: NasaDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_getNasaData() = runTest {
        Mockito.`when`(repository.getNasaData()).thenReturn(Result.Success(ArrayList()))

        val sut = MainViewModel(repository)
        sut.getNasaData()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.nasaDataLiveData.getOrAwaitValue()

        Assert.assertEquals(0, result.data!!.size)
    }

    @Test
    fun test_getNasaData_Error() = runTest {
        Mockito.`when`(repository.getNasaData()).thenReturn(Result.Error("API Error"))

        val sut = MainViewModel(repository)
        sut.getNasaData()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.nasaDataLiveData.getOrAwaitValue()

        Assert.assertEquals(true, result is Result.Error)
        Assert.assertEquals("API Error", result.errorMessage)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}