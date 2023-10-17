package com.khaled_sho.testmedicalapp.core.base.ui


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.khaled_sho.testmedicalapp.R
import com.khaled_sho.testmedicalapp.core.base.model.DataError
import com.khaled_sho.testmedicalapp.core.base.model.Success
import com.khaled_sho.testmedicalapp.ui.theme.TestMedicalAppTheme
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColorDark
import java.lang.reflect.ParameterizedType

abstract class BaseComponentActivity<VM : BaseViewModel> : ComponentActivity() {

    lateinit var viewModel: VM

    //override in child class if you don't want to use global loading state
    open val wantToShowCustomLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(getViewModelOwner())[getViewModelClass()]
        setContent {
            TestMedicalAppTheme {
                SetStatusBarColor(color = myPrimaryColorDark)
                ProvideCompose()
                SetUpLoadingDialog()
                SetUpErrorDialog()
            }
        }
    }

    @Composable
    fun SetStatusBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(color)
        }
    }


    open fun getViewModelOwner(): ViewModelStoreOwner = this
    private fun getViewModelClass(): Class<VM> {
        val type = if (javaClass.genericSuperclass is ParameterizedType) {
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        } else {
            javaClass.genericSuperclass
        }
        return type as Class<VM>
    }

    @SuppressLint("VisibleForTests")
    @Composable
    private fun SetUpLoadingDialog() {
        if (!wantToShowCustomLoading) {
            val loadingValue = viewModel.showDialogLoadingPrivate.observeAsState()
            if (loadingValue.value == true) {
                ShowLoading()
            }
        }
    }


    @Composable
    protected open fun ShowLoading() {
        Dialog(
            onDismissRequest = { viewModel.hideLoading() },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }


    @Composable
    abstract fun ProvideCompose()

    @Composable
    abstract fun ProvideComposeLightPreview()


    inline fun <reified T : ComponentActivity> Context.startActivity(block: Intent.() -> Unit = {}) {
        startActivity(Intent(this, T::class.java).apply(block))
    }


    @Composable
    private fun SetUpErrorDialog() {
        var dialogState = false
        var errorDescription = ""
        val vmLoadinState = viewModel.showMessageDialog.observeAsState()
        when (vmLoadinState.value) {
            is DataError -> {
                errorDescription = (vmLoadinState.value as DataError<String>).errorDescription
                dialogState = true
            }

            is Success -> {
                dialogState = false
            }

            else -> {}
        }
        if (dialogState) {
            ShowErrorDialog(errorDescription)
        }
    }


    @Composable
    private fun ShowErrorDialog(errorDescription: String) {

        AlertDialog(

            onDismissRequest = {
            },

            containerColor = DarkGray,
            iconContentColor = White,
            titleContentColor = White,

            title = {
                Text(stringResource(R.string.error), style = MaterialTheme.typography.headlineSmall)
            },
            text = {
                Text(errorDescription, fontSize = 16.sp)
            },
            confirmButton = {
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.hideMessageDialog(Success(""))
                    }) {
                    Text(
                        "Ok",
                        style = MaterialTheme.typography.bodyMedium,
                        color = White,
                    )
                }
            }
        )
    }
}