package com.shakib.samsungrndtask.presentation.views.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmptyViewKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun emptyView_displaysCorrectContent() {
        // When: The composable is loaded
        composeTestRule.setContent {
            EmptyView()
        }

        // Then: Verify content is displayed correctly
        composeTestRule.onNodeWithText("Sorry! No item found").assertIsDisplayed()

    }
}
