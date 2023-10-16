package com.khaled_sho.testmedicalapp.main.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.khaled_sho.testmedicalapp.auth.data.usecase.AuthUseCase
import com.khaled_sho.testmedicalapp.core.base.model.BaseModel
import com.khaled_sho.testmedicalapp.core.base.model.Result
import com.khaled_sho.testmedicalapp.core.base.ui.BaseViewModel
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import com.khaled_sho.testmedicalapp.main.data.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val mainUseCase: MainUseCase,
) : BaseViewModel() {

    val problemsResult = MediatorLiveData<Result<BaseModel<List<AssociatedDrug>>>>()

    init {
        getProblems()
    }

    fun getProblems() {
        showLoading()
        viewModelScope.launch(exceptionHandler) {
            mainUseCase.getProblems().collect {
                hideLoading()
                problemsResult.postValue(it)
            }
        }
    }

    fun logout() {
        showLoading()
        viewModelScope.launch(exceptionHandler) {
            authUseCase.logout()
        }
    }
}