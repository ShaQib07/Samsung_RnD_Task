package com.shakib.samsungrndtask.presentation.views.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorViewKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorView_displaysCorrectContent() {
        // Given: A message
        val message = "Oops! Something went wrong"

        // When: The composable is loaded
        composeTestRule.setContent {
            ErrorView(message = message)
        }

        // Then: Verify content is displayed correctly
        composeTestRule.onNodeWithText(message).assertIsDisplayed()
    }

}