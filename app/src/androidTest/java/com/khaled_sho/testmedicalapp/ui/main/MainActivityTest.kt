package com.khaled_sho.testmedicalapp.ui.main

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.khaled_sho.testmedicalapp.R
import com.khaled_sho.testmedicalapp.main.data.model.AssociatedDrug
import com.khaled_sho.testmedicalapp.main.ui.MainActivity
import com.khaled_sho.testmedicalapp.main.ui.composable.ShowList
import com.khaled_sho.testmedicalapp.ui.BaseInstrument
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@HiltAndroidTest
class MainActivityTest : BaseInstrument(){


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get : Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    lateinit var activity: ComponentActivity

    lateinit var navController: TestNavHostController
    @Before
    override fun setUp(){
        activity = composeTestRule.activity
    }

    @Test
    fun testShowListIfListIsEmpty() {
        activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            ShowList(navController, emptyList())
            composeTestRule.onNodeWithTag(stringResource(R.string.test_list_tag))
                .onChildren()
                .assertCountEquals(0)
        }
    }

    @Test
    fun testShowListIfListIsNotEmpty() {

        activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            ShowList(navController, getDrugsList())
            composeTestRule.onNodeWithTag(stringResource(R.string.test_list_tag))
                .onChildren()
                .assertCountEquals(3)
        }
    }

    @Test
    fun testShowListIfItemIsClicked() {
        activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            ShowList(navController, getDrugsList())
            composeTestRule.onNodeWithTag(stringResource(R.string.test_list_tag))
                .onChildAt(0)
                .performClick()
                .assertIsNotDisplayed()
        }
    }


    private fun getDrugsList(): List<AssociatedDrug> = listOf(
            AssociatedDrug("first","first_dose","first_strength"),
            AssociatedDrug("second","second_dose","second_strength"),
            AssociatedDrug("third","third_dose","third_strength"),
        )


}