package com.khaled_sho.testmedicalapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.khaled_sho.testmedicalapp.auth.data.usecase.AuthUseCase
import com.khaled_sho.testmedicalapp.coroutines.CoroutineTestRule
import com.khaled_sho.testmedicalapp.main.data.usecase.MainUseCase
import com.khaled_sho.testmedicalapp.main.ui.MainViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {


    lateinit var viewModelUnderTest: MainViewModel

    lateinit var mainUseCase: MainUseCase
    lateinit var authUseCase: AuthUseCase

    @ExperimentalCoroutinesApi
    @get : Rule(order = 1)
    open val mainCoroutineRule = CoroutineTestRule()

    @get : Rule(order = 2)
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainUseCase = mockk()
        authUseCase = mockk()
        viewModelUnderTest = MainViewModel(authUseCase, mainUseCase)
    }



    @Test
    fun `on logout,logout method call,return success`() {

        //Given
         coEvery {
             authUseCase.logout()
         }

        //when
        viewModelUnderTest.logout()

        //then
        val result = viewModelUnderTest.logoutPrivate.value
        org.junit.Assert.assertEquals(true, result)

    }

}