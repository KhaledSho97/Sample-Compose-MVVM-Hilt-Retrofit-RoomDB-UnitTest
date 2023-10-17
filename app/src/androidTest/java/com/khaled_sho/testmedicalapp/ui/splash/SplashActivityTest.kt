package com.khaled_sho.testmedicalapp.ui.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.khaled_sho.testmedicalapp.R
import com.khaled_sho.testmedicalapp.auth.ui.AuthActivity
import com.khaled_sho.testmedicalapp.landing.ui.SplashActivity
import com.khaled_sho.testmedicalapp.ui.BaseInstrument
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@HiltAndroidTest
class SplashActivityTest : BaseInstrument() {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get : Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<SplashActivity>()

    override fun setUp() {}

    @Test
    fun splash_title_isDisplayed() {
        composeTestRule
            .onNodeWithText(
                composeTestRule.activity.getString(R.string.app_name)
            )
            .assertIsDisplayed()
    }

    @Test
    fun splash_circularProgress_isDisplayed() {
        composeTestRule.onRoot().printToLog("MY SPLASH TAG")
        composeTestRule
            .onNodeWithTag(composeTestRule.activity.getString(R.string.test_tag_circular_progress))
            .assertIsDisplayed()
    }
}