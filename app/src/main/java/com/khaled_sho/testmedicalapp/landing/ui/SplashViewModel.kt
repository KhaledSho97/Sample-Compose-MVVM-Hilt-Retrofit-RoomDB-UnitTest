package com.khaled_sho.testmedicalapp.landing.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.khaled_sho.testmedicalapp.auth.data.model.User
import com.khaled_sho.testmedicalapp.auth.data.usecase.AuthUseCase
import com.khaled_sho.testmedicalapp.core.base.model.Result
import com.khaled_sho.testmedicalapp.core.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class SplashViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : BaseViewModel() {

    val loginResponse = MediatorLiveData<Result<User>>()

    fun decideActivity() {
        showLoading()
        viewModelScope.launch(exceptionHandler) {
            authUseCase.getLoggedUser().collect { loginResult ->
                hideLoading()
                loginResponse.postValue(loginResult)
            }
        }
    }
}