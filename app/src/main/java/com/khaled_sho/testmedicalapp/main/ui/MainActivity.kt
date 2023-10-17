package com.khaled_sho.testmedicalapp.main.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.khaled_sho.testmedicalapp.R
import com.khaled_sho.testmedicalapp.core.base.ui.BaseComponentActivity
import com.khaled_sho.testmedicalapp.core.data.local.UserCache
import com.khaled_sho.testmedicalapp.landing.ui.SplashActivity
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import com.khaled_sho.testmedicalapp.main.ui.composable.DetailsView
import com.khaled_sho.testmedicalapp.main.ui.composable.MainHeader
import com.khaled_sho.testmedicalapp.main.ui.composable.ShowList
import com.khaled_sho.testmedicalapp.ui.theme.TestMedicalAppTheme
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColor
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColorDark
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseComponentActivity<MainViewModel>() {

    @Composable
    override fun ProvideCompose() {
        TestMedicalAppTheme {
            MainNavHost()
        }
    }

    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {
        TestMedicalAppTheme {
            MainNavHost()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun logout() {
        viewModel.logout()
        startActivity<SplashActivity>()
        finish()
    }

    @Composable
    fun MainView(
        navController: NavController
    ) {
        val diabetesAssociatedDrugs = remember { mutableStateListOf<AssociatedDrug>() }
        LaunchedEffect(viewModel) {
            viewModel.problemsResult.observe(this@MainActivity) {
                it?.let {
                    it.data?.data?.let { list ->
                        diabetesAssociatedDrugs.clear()
                        diabetesAssociatedDrugs.addAll(list)
                    }
                }
            }
        }

        SetStatusBarColor(color = myPrimaryColorDark)
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val userName = remember {
            mutableStateOf(UserCache.user?.userName ?: "User")
        }
        Scaffold(
            modifier = Modifier.fillMaxSize().testTag(stringResource(id = R.string.main_view_tag)),
            containerColor = Color.White,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = myPrimaryColor,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text(
                            stringResource(id = R.string.app_name),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            logout()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                tint = Color.White,
                                contentDescription = "Logout"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
        ) { innerPadding ->
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                Column {
                    MainHeader(modifier = Modifier, userName.value)
                    ShowList(navController, listOfAssociatedDrug = diabetesAssociatedDrugs)
                }
            }
        }
    }

    @Composable
    fun MainNavHost() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "MainView",
        ) {
            composable(
                route = "MainView",
                content = { MainView(navController) },
            )
            composable(
                route = "ProfileDetails/{name}/{strength}/{dose}/{image}",
                arguments = listOf(
                    navArgument("name") {
                        type = NavType.StringType
                    },
                    navArgument("strength") {
                        type = NavType.StringType
                    },
                    navArgument("dose") {
                        type = NavType.StringType
                    },
                    navArgument("image") {
                        type = NavType.IntType
                    },
                )
            ) {
                val name = it.arguments?.getString("name")!!
                val strength = it.arguments?.getString("strength")!!
                val dose = it.arguments?.getString("dose")!!
                val image = it.arguments?.getInt("image")!!
                DetailsView(Modifier, name, strength, dose, image)
            }
        }
    }


    companion object {
        @JvmStatic
        fun newMainIntent(context: Context) = Intent(
            context, MainActivity::class.java
        )
    }
}