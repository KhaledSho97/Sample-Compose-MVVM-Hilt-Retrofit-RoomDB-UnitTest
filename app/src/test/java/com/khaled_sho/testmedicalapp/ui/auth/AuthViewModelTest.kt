package com.khaled_sho.testmedicalapp.ui.auth


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.foundation.ExperimentalFoundationApi
import com.khaled_sho.testmedicalapp.BaseInstrument
import com.khaled_sho.testmedicalapp.auth.data.usecase.AuthUseCase
import com.khaled_sho.testmedicalapp.auth.ui.AuthViewModel
import com.khaled_sho.testmedicalapp.core.base.model.DataError
import com.khaled_sho.testmedicalapp.core.base.model.Resource
import com.khaled_sho.testmedicalapp.core.util.ENTER_EMAIL_ID
import com.khaled_sho.testmedicalapp.core.util.ENTER_PASSWORD
import com.khaled_sho.testmedicalapp.core.util.NO_INTERNET_CONNECTION
import com.khaled_sho.testmedicalapp.coroutines.CoroutineTestRule
import com.khaled_sho.testmedicalapp.main.ui.MainViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalFoundationApi::class)
class AuthViewModelTest : BaseInstrument() {

    lateinit var authUseCase: AuthUseCase

    lateinit var viewModelUnderTest: AuthViewModel
    @ExperimentalCoroutinesApi
    override fun setUp() {
        authUseCase = mockk()
        viewModelUnderTest =  spyk(AuthViewModel(authUseCase))
    }

    @ExperimentalCoroutinesApi
    @get : Rule(order = 1)
    open val mainCoroutineRule = CoroutineTestRule()

    @get : Rule(order = 2)
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `onSignBtnClick , empty email and empty pass, return DataError ENTER_EMAIL_ID `() {
        //Given

        //when
        viewModelUnderTest.onSignInBtnClick("", "", "")
        viewModelUnderTest.showMessageDialog.observeForever {}

        //then
        val loginfail = viewModelUnderTest.showMessageDialog.value?.errorDescription

        assertEquals(ENTER_EMAIL_ID, loginfail)


    }
    @Test
    fun `onSignBtnClick , empty email, return DataError ENTER_EMAIL_ID `() {
        //Given

        //when
        viewModelUnderTest.onSignInBtnClick("" ,"Khaled","pas")
        viewModelUnderTest.showMessageDialog.observeForever {}

        //then
        val loginfail = viewModelUnderTest.showMessageDialog.value?.errorDescription

        assertEquals(ENTER_EMAIL_ID, loginfail)


    }


    @Test
    fun `onSignBtnClick , empty pass, return DataError ENTER_PASSWORD `() {
        //Given

        //when
        viewModelUnderTest.onSignInBtnClick("emal", "Khaled", "")
        viewModelUnderTest.showMessageDialog.observeForever {}

        //then
        val loginfail = viewModelUnderTest.showMessageDialog.value?.errorDescription

        assertEquals(ENTER_PASSWORD, loginfail)
    }

}