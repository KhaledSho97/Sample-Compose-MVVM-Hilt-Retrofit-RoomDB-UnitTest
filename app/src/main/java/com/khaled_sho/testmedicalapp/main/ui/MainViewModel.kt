package com.khaled_sho.testmedicalapp.main.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khaled_sho.testmedicalapp.auth.data.usecase.AuthUseCase
import com.khaled_sho.testmedicalapp.core.base.model.BaseModel
import com.khaled_sho.testmedicalapp.core.base.model.Resource
import com.khaled_sho.testmedicalapp.core.base.model.Result
import com.khaled_sho.testmedicalapp.core.base.model.Success
import com.khaled_sho.testmedicalapp.core.base.ui.BaseViewModel
import com.khaled_sho.testmedicalapp.core.util.SingleEvent
import com.khaled_sho.testmedicalapp.core.util.SingleLiveEvent
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import com.khaled_sho.testmedicalapp.main.data.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val mainUseCase: MainUseCase,
) : BaseViewModel() {

    val problemsResult = MediatorLiveData<Result<BaseModel<List<AssociatedDrug>>>>()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val logoutPrivate = MutableLiveData(true)


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
            authUseCase.logout().collect {
                logoutPrivate.value = it.data ?: false
            }
        }
    }
}