package com.khaled_sho.testmedicalapp.main.ui

import androidx.lifecycle.viewModelScope
import com.khaled_sho.testmedicalapp.auth.data.usecase.AuthUseCase
import com.khaled_sho.testmedicalapp.core.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : BaseViewModel() {

    fun logout() {
        showLoading()
        viewModelScope.launch(exceptionHandler) {
            authUseCase.logout()
        }
    }
}