package com.khaled_sho.testmedicalapp.main.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.khaled_sho.testmedicalapp.core.base.ui.BaseComponentActivity
import com.khaled_sho.testmedicalapp.core.data.local.UserCache
import com.khaled_sho.testmedicalapp.landing.ui.SplashActivity
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import com.khaled_sho.testmedicalapp.main.data.model.ProblemsResponse
import com.khaled_sho.testmedicalapp.ui.theme.TestMedicalAppTheme
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColor
import com.khaled_sho.testmedicalapp.ui.theme.myPrimaryColorDark
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class MainActivity : BaseComponentActivity<MainViewModel>() {

    @OptIn(ExperimentalAnimationApi::class)
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


    @SuppressLint("SimpleDateFormat")
    @Composable
    fun MainView(
        navController: NavController
    ) {
        val listOfAssociatedDrug = remember { mutableStateListOf<AssociatedDrug>() }
        LaunchedEffect(viewModel) {
            viewModel.problemsResult.observe(this@MainActivity) {
                it?.let {
                    it.data?.data?.let {
                        val s =
                            it.problems!![0].diabetes[0].medications[0].medicationsClasses[0]
                                .className[0].associatedDrug[0].name!!
                        Toast.makeText(this@MainActivity, "Doneee ! $s", Toast.LENGTH_SHORT).show()
                        DummyData(it, listOfAssociatedDrug)


                    }
                    //viewModel.problemsResult.postValue(null)
                }
            }

        }

        SetStatusBarColor(color = myPrimaryColorDark)
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val userName = UserCache.user?.userName ?: "User"
        Scaffold(
            modifier = Modifier.fillMaxSize(),
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
                        IconButton(onClick = {
                            logout()
                        }) {
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
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                /*ShowMyCircularProgress(
                    percentage = 0.8f,
                    number = 100,
                    animationDuration = 3000
                )
                GroupedList()*/
                LazyColumn {
                    itemsIndexed(listOfAssociatedDrug) { index, drug ->
                        ItemDrugCard(drug, onItemClicked = {
                            val sdf =
                                SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss z")
                            val currentDateAndTime = sdf.format(Date()).toString()
                            navController.navigate("ProfileDetails/khaled/1/$currentDateAndTime")
                        })
                    }
                }
            }

        }
    }

    fun DummyData(
        problemsResponse: ProblemsResponse,
        listOfAssociatedDrug: MutableList<AssociatedDrug>
    ) {
        listOfAssociatedDrug.clear()
        problemsResponse.let {
            it.problems?.let { problems ->
                if (problems.isNotEmpty()) {
                    for (problem in it.problems!!) {
                        if (problem.diabetes.isNotEmpty()) {
                            for (diabetesItem in problem.diabetes) {
                                if (diabetesItem.medications.isNotEmpty()) {
                                    for (medication in diabetesItem.medications) {
                                        if (medication.medicationsClasses.isNotEmpty()) {
                                            for (classItem in medication.medicationsClasses) {
                                                if (classItem.className.isNotEmpty()) {
                                                    for (classNameItem in classItem.className) {
                                                        if (classNameItem.associatedDrug.isNotEmpty()) {
                                                            for (drug in classNameItem.associatedDrug) {
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                            }
                                                        }
                                                        if (classNameItem.associatedDrug2.isNotEmpty()) {
                                                            for (drug in classNameItem.associatedDrug2) {
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                                listOfAssociatedDrug.add(
                                                                    drug
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
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
                route = "ProfileDetails/{name}/{userId}/{timestamp}",
                arguments = listOf(
                    navArgument("name") {
                        type = NavType.StringType
                    },
                    navArgument("userId") {
                        type = NavType.StringType
                    },
                    navArgument("timestamp") {
                        type = NavType.StringType
                    },
                )
            ) {
                val name = it.arguments?.getString("name")!!
                val userId = it.arguments?.getString("userId")!!
                val timestamp = it.arguments?.getString("timestamp")!!
                Greeting(name, userId, timestamp)
            }
        }
    }

    @Composable
    fun Greeting(name: String, userId: String, timestamp: String) {
        Text(
            text = "Welcome $name\nYour Id is: $userId\n$timestamp"
        )
    }

    companion object {
        @JvmStatic
        fun newMainIntent(context: Context) = Intent(
            context, MainActivity::class.java
        )
    }
}