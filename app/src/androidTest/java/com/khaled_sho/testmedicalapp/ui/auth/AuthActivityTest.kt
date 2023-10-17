package com.khaled_sho.testmedicalapp.ui.auth

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.khaled_sho.testmedicalapp.ui.BaseInstrument
import com.khaled_sho.testmedicalapp.R
import com.khaled_sho.testmedicalapp.auth.ui.AuthActivity
import com.khaled_sho.testmedicalapp.core.util.ENTER_EMAIL_ID
import com.khaled_sho.testmedicalapp.core.util.ENTER_PASSWORD
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalFoundationApi
@HiltAndroidTest
class AuthActivityTest : BaseInstrument() {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get : Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<AuthActivity>()
    lateinit var activity: ComponentActivity

    @Before
    override fun setUp() {
        activity = composeTestRule.activity
        composeTestRule
            .onNodeWithTag(activity.getString(R.string.email_address))
            .performTextClearance()
        composeTestRule
            .onNodeWithTag(activity.getString(R.string.password))
            .performTextClearance()
    }

    @Test
    fun login_emptyEmail_showMessage() {
        composeTestRule
            .onNodeWithTag(activity.getString(R.string.password))
            .performTextInput("12323")

        composeTestRule.onNodeWithTag(activity.getString(R.string.sign_in))
            .performClick()

        composeTestRule.onNodeWithText(ENTER_EMAIL_ID)
            .assertIsDisplayed()


    }


    @Test
    fun login_emptyPass_showMessage() {
        composeTestRule
            .onNodeWithTag(activity.getString(R.string.email_address))
            .performTextInput("12323")

        composeTestRule.onNodeWithTag(activity.getString(R.string.sign_in))
            .performClick()

        composeTestRule.onNodeWithText(ENTER_PASSWORD)
            .assertIsDisplayed()
    }

    @Test
    fun login_filledEmailAndPass_openMainView() {
        composeTestRule
            .onNodeWithTag(activity.getString(R.string.email_address))
            .performTextInput("khaled.97.sy@gmail.com")

        composeTestRule
            .onNodeWithTag(activity.getString(R.string.password))
            .performTextInput("1234@k")

        composeTestRule.onNodeWithTag(activity.getString(R.string.sign_in))
            .performClick()

        composeTestRule.onNodeWithTag(activity.getString(R.string.main_view_tag))
            .assertIsDisplayed()

    }


}