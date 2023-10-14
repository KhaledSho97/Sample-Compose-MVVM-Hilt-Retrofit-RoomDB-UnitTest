package com.khaled_sho.testmedicalapp.auth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khaled_sho.testmedicalapp.auth.data.model.User
import com.khaled_sho.testmedicalapp.auth.data.usecase.AuthUseCase
import com.khaled_sho.testmedicalapp.core.base.model.DataError
import com.khaled_sho.testmedicalapp.core.base.model.Result
import com.khaled_sho.testmedicalapp.core.base.model.Status
import com.khaled_sho.testmedicalapp.core.base.ui.BaseViewModel
import com.khaled_sho.testmedicalapp.core.util.ENTER_EMAIL_ID
import com.khaled_sho.testmedicalapp.core.util.ENTER_NAME
import com.khaled_sho.testmedicalapp.core.util.ENTER_PASSWORD
import com.khaled_sho.testmedicalapp.core.util.SOMETHING_WENT_WRONG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : BaseViewModel() {


    val loginResponse = MutableLiveData<User>()


    fun onSignInBtnClick(email: String, userName: String, password: String) {
        when {
            email.isEmpty() -> {
                showMessageDialog(DataError(ENTER_EMAIL_ID))
            }

            userName.isEmpty() -> {
                showMessageDialog(DataError(ENTER_NAME))
            }

            password.isEmpty() -> {
                showMessageDialog(DataError(ENTER_PASSWORD))

            }

            else -> {
                showLoading()
                viewModelScope.launch(exceptionHandler) {
                    val user = User()
                    user.email = email
                    user.password = password
                    user.userName = userName
                    authUseCase.getLoggedUser(user).collect { loginResult ->
                        hideLoading()
                        when (loginResult.status) {
                            Status.SUCCESS -> {
                                if (loginResult.data != null) {
                                    loginResult.data.let {
                                        loginResponse.value = it
                                    }
                                } else {
                                    showErrorInBackThread(Result.error(SOMETHING_WENT_WRONG, null))
                                }
                            }

                            Status.ERROR, Status.AUTHORIZATION -> {
                                showErrorInBackThread(loginResult)
                            }

                            else -> {}
                        }
                    }

                }


            }
        }

    }


}