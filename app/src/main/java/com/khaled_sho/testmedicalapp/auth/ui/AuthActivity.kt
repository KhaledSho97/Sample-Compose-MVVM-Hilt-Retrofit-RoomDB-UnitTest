package com.khaled_sho.testmedicalapp.auth.ui

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.khaled_sho.testmedicalapp.R
import com.khaled_sho.testmedicalapp.core.base.ui.BaseComponentActivity
import com.khaled_sho.testmedicalapp.main.ui.MainActivity
import com.khaled_sho.testmedicalapp.ui.theme.TestMedicalAppTheme
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColor
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColorDark
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseComponentActivity<AuthViewModel>() {


    @Composable
    override fun ProvideCompose() {
        LaunchedEffect(viewModel) {
            viewModel.loginResponse.observe(this@AuthActivity) {
                Toast.makeText(this@AuthActivity, "LoggedIn", Toast.LENGTH_SHORT).show()
                startActivity(MainActivity.newMainIntent(this@AuthActivity))
                finish()
            }
        }


        LoginCompose {
            TopImageAndText()

            var txtAccountNo by remember {
                mutableStateOf("khaled.97.sy@gmail.com")
            }
            TextFieldEmail(txtAccountNo) { txtAccountNo = it }

            var txtUserName by remember {
                mutableStateOf("Khaled Shoushara")
            }
            TextFieldUserName(txtUserName) { txtUserName = it }

            var txtPass by remember { mutableStateOf("1234@k") }
            TextFieldPassword(txtPass) {
                txtPass = it
            }

            RegistrationButton {
                viewModel.onSignInBtnClick(
                    txtAccountNo.trim(),
                    txtUserName.trim(),
                    txtPass.trim()
                )
            }
        }

    }

    @Composable
    fun LoginCompose(childrenCompose: @Composable () -> Unit) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.dp_20)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            childrenCompose()
        }
    }


    @Composable
    private fun RegistrationButton(onClick: () -> Unit = {}) {

        Button(modifier = Modifier
            .testTag(stringResource(id = R.string.sign_in))
            .width(dimensionResource(R.dimen.dp_150)),
            colors = ButtonDefaults
                .buttonColors(myPrimaryColorDark),
            onClick = { onClick() }) {
            Text(
                text = stringResource(id = R.string.sign_in).uppercase(),
                style = TextStyle(color = Color.White)
            )
        }
        Spacer(
            modifier = Modifier
                .height(dimensionResource(R.dimen.dp_30))
        )
    }


    @Composable
    private fun TextFieldPassword(txtPass: String, setPass: (String) -> Unit = {}) {
        OutlinedTextField(modifier = Modifier
            .testTag(stringResource(R.string.password))
            .fillMaxWidth()
            .wrapContentHeight(),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = myPrimaryColorDark
            ),
            maxLines = 1,
            singleLine = true,
            value = txtPass,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { setPass(it) },
            label = {
                Text(
                    stringResource(R.string.password),
                    style = TextStyle(color = myPrimaryColorDark)
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.password),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_lock_24),
                    contentDescription = "", tint = MaterialTheme.colorScheme.onSecondary
                )
            })

        Spacer(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.dp_40))
        )


    }

    @Composable
    private fun TopImageAndText() {
        Image(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.dp_70))
                .size(dimensionResource(id = R.dimen.dp_100)),
            painter = painterResource(id = R.drawable.medical),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp_30)))

        Text(
            modifier = Modifier.width(IntrinsicSize.Max),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.headlineMedium,
            color = myPrimaryColor
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp_60)))


    }

    @Composable
    private fun TextFieldEmail(txtAccountNo: String, setAcc: (String) -> Unit = {}) {
        OutlinedTextField(modifier = Modifier
            .testTag(stringResource(R.string.email_address))
            .fillMaxWidth()
            .wrapContentHeight(),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = myPrimaryColorDark
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            label = {
                Text(
                    stringResource(R.string.email_address),
                    style = TextStyle(color = myPrimaryColorDark),
                )
            },
            singleLine = true,
            value = txtAccountNo,
            onValueChange = { setAcc(it) },
            placeholder = {
                Text(
                    text = stringResource(R.string.email_address),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_person_24),
                    contentDescription = "", tint = MaterialTheme.colorScheme.onSecondary
                )
            })
        Spacer(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.dp_20))
        )
    }


    @Composable
    private fun TextFieldUserName(txtAccountNo: String, setAcc: (String) -> Unit = {}) {
        OutlinedTextField(modifier = Modifier
            .testTag(stringResource(R.string.user_name))
            .fillMaxWidth()
            .wrapContentHeight(),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = myPrimaryColorDark
            ),
            singleLine = true,
            value = txtAccountNo,
            label = {
                Text(
                    stringResource(R.string.user_name),
                    style = TextStyle(color = myPrimaryColorDark)
                )
            },
            onValueChange = { setAcc(it) },
            placeholder = {
                Text(
                    text = stringResource(R.string.user_name),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_person_24),
                    contentDescription = "", tint = MaterialTheme.colorScheme.onSecondary
                )
            })
        Spacer(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.dp_20))
        )
    }


    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {
        TestMedicalAppTheme {
            LoginCompose {
                TopImageAndText()
                TextFieldEmail("")
                TextFieldUserName("")
                TextFieldPassword("")
                RegistrationButton()
            }
        }
    }
}