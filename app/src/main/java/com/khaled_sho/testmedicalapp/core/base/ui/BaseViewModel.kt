package com.khaled_sho.testmedicalapp.core.base.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khaled_sho.testmedicalapp.core.base.model.DataError
import com.khaled_sho.testmedicalapp.core.base.model.Resource
import com.khaled_sho.testmedicalapp.core.base.model.Result
import com.khaled_sho.testmedicalapp.core.base.model.Success
import com.khaled_sho.testmedicalapp.core.util.SOMETHING_WENT_WRONG
import com.khaled_sho.testmedicalapp.core.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val showDialogLoadingPrivate = MutableLiveData(false)

    val showMessageDialog = MutableLiveData<Resource<String>>()
    var errorLiveData: SingleLiveEvent<String> = SingleLiveEvent()

    var apiRequestInProgress: SingleLiveEvent<Boolean> = SingleLiveEvent()

    private val onErrorDialogDismissPrivate = MutableLiveData<Resource<Boolean>>()
    val onErrorDialogDismiss: LiveData<Resource<Boolean>> get() = onErrorDialogDismissPrivate

    protected val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        hideLoading()
        showMessageDialog(DataError(SOMETHING_WENT_WRONG))

    }


    fun isLoading(): Boolean {
        return showDialogLoadingPrivate.value!!
    }


    fun showLoading() {
        if (!showDialogLoadingPrivate.value!!) {
            showDialogLoadingPrivate.value = true
        }

    }

    fun hideLoading() {
        if (showDialogLoadingPrivate.value!!) {
            showDialogLoadingPrivate.value = false
        }
    }


    fun showMessageDialog(dataError: DataError<String>) {
        showMessageDialog.value = dataError
    }

    fun showPostMessageDialog(dataError: DataError<String>) {
        showMessageDialog.value = dataError
    }

    fun hideMessageDialog(success: Success<String>) {
        showMessageDialog.value = success
    }


    fun <T> showError(result: Result<T>) {
        errorLiveData.value = result.message ?: "Unknown error"
    }

    fun <T> showErrorInBackThread(result: Result<T>) {
        if (result.message != null) {
            result.message.let {
                errorLiveData.postValue(it)
            }
        } else {
            errorLiveData.postValue("Unknown error")
        }
    }
}