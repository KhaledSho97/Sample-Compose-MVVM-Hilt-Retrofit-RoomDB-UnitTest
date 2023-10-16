package com.khaled_sho.testmedicalapp.landing.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.khaled_sho.testmedicalapp.R
import com.khaled_sho.testmedicalapp.auth.ui.AuthActivity
import com.khaled_sho.testmedicalapp.core.base.model.Status
import com.khaled_sho.testmedicalapp.core.base.ui.BaseComponentActivity
import com.khaled_sho.testmedicalapp.main.ui.MainActivity
import com.khaled_sho.testmedicalapp.ui.theme.TestMedicalAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@ExperimentalFoundationApi
@AndroidEntryPoint
class SplashActivity : BaseComponentActivity<SplashViewModel>() {

    override val wantToShowCustomLoading = true

    @Composable
    override fun ProvideCompose() {
        viewModel.decideActivity()

        SplashCompose {
            ImageAndAppName {
                val loadingValue = viewModel.isLoading()

                LaunchedEffect(viewModel) {
                    delay(1000)
                    viewModel.loginResponse.observe(this@SplashActivity) {
                        it?.let {
                            when (it.status) {
                                Status.SUCCESS -> {
                                    startActivity<MainActivity>()
                                    finish()
                                }

                                Status.ERROR, Status.AUTHORIZATION -> {
                                    startActivity<AuthActivity>()
                                    finish()
                                }

                                else -> {

                                }
                            }
                            viewModel.loginResponse.postValue(null)
                        }
                    }
                }

                if (loadingValue) {
                    CircularProgressIndicator(
                        Modifier.testTag(
                            getString(R.string.test_tag_circular_progress)
                        )
                    )
                }
            }
        }
    }

    @Composable
    private fun SplashCompose(ChildrenCompose: @Composable () -> Unit) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            ChildrenCompose()

        }
    }


    @Composable
    private fun ImageAndAppName(showLoading: @Composable () -> Unit) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.medical),
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.dp_100))
                    .width(dimensionResource(id = R.dimen.dp_120))
                    .height(dimensionResource(id = R.dimen.dp_120)),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.width(IntrinsicSize.Max),
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dp_20)))
            showLoading()

        }
    }


    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {
        TestMedicalAppTheme {
            SplashCompose {
                ImageAndAppName {
                    CircularProgressIndicator()

                }
            }
        }
    }
}

