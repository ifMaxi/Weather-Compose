package com.maxidev.weather

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FakeModelWeatherRepositoryTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

//    @MockK
//    private lateinit var fakeRepositoryImpl: FakeRepositoryImpl

    @MockK
    private lateinit var fakeRepository: FakeRepository

    @MockK
    private var model = mockk<FakeModelWeather>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        //fakeRepositoryImpl = FakeRepositoryImpl()
    }

    @After
    fun close() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Check if the data sent is correct`() = runTest {
        val city = "Venado Tuerto"

        coEvery { fakeRepository.repWeather(any()) } returns model

        fakeRepository.repWeather(city)

        coVerify(exactly = 1) { fakeRepository.repWeather(city) }
    }

    @Test
    fun `throws an exception if the data does not match`() = runTest {
        val city = "Texas"

        coEvery { fakeRepository.repWeather(any()) } throws Exception("Houston we have a problem")

        fakeRepository.repWeather(city)

        coVerify(exactly = 1) { fakeRepository.repWeather(any()) }
        coVerify(exactly = 1) { fakeRepository.repWeather(city) }
    }
}