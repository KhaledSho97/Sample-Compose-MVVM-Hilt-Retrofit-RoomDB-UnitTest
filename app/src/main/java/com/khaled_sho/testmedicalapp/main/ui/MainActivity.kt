package com.khaled_sho.testmedicalapp.main.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khaled_sho.testmedicalapp.core.base.ui.BaseComponentActivity
import com.khaled_sho.testmedicalapp.core.data.local.UserCache
import com.khaled_sho.testmedicalapp.landing.ui.SplashActivity
import com.khaled_sho.testmedicalapp.ui.theme.Purple80
import com.khaled_sho.testmedicalapp.ui.theme.TestMedicalAppTheme
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColor
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColorDark
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseComponentActivity<MainViewModel>() {


    @Composable
    fun MainView() {
        SetStatusBarColor(color = myPrimaryColorDark)
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val userName = UserCache.user?.userName ?: "User"
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = Color.White,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = myPrimaryColor,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text(
                            "Welcome $userName",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        IconButton(onClick = { logout() }) {
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                tint = Color.White,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
        ) { innerPadding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                ShowMyCircularProgress(
                    percentage = 0.8f,
                    number = 100,
                    animationDuration = 3000
                )
            }
        }
    }

    @Composable
    override fun ProvideCompose() {
        TestMedicalAppTheme {
            MainView()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun logout() {
        viewModel.logout()
        startActivity<SplashActivity>()
        finish()
    }


    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {
        TestMedicalAppTheme {
            MainView()
        }
    }

    @Composable
    fun ShowMyCircularProgress(
        percentage: Float,
        number: Int,
        fontSize: TextUnit = 28.sp,
        radius: Dp = 50.dp,
        color: Color = Color.Green,
        strokeWidth: Dp = 8.dp,
        animationDuration: Int = 1000,
        animationDelay: Int = 0
    ) {
        var animationPlayed by remember {
            mutableStateOf(false)
        }
        val curPercentage = animateFloatAsState(
            targetValue = if (animationPlayed) percentage else 0f,
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDelay
            ), label = ""
        )

        LaunchedEffect(key1 = true) {
            animationPlayed = true
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(radius * 2f)
        ) {
            Canvas(modifier = Modifier.size(radius * 2f)) {
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360 * curPercentage.value,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
            Text(
                text = (curPercentage.value * number).toInt().toString(),
                color = Color.Black,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }

    companion object {
        @JvmStatic
        fun newMainIntent(context: Context) = Intent(
            context, MainActivity::class.java
        )
    }
}