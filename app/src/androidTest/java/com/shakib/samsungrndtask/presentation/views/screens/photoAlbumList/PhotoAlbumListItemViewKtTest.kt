package com.shakib.samsungrndtask.presentation.views.screens.photoAlbumList

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shakib.samsungrndtask.presentation.model.PhotoAlbum
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotoAlbumListItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun photoAlbumListItemView_displaysCorrectContent() {
        // Given: A PhotoAlbum object
        val photoAlbum = PhotoAlbum(
            id = 1,
            albumName = "Album Name",
            userName = "User Name",
            photoTitle = "Photo Title",
            photoThumbnailUrl = ""
        )

        // When: The composable is loaded
        composeTestRule.setContent {
            PhotoAlbumListItemView(photoAlbum = photoAlbum)
        }

        // Then: Verify content is displayed correctly
        composeTestRule.onNodeWithContentDescription(photoAlbum.photoTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText("From the album").assertIsDisplayed()
        composeTestRule.onNodeWithText(photoAlbum.albumName).assertIsDisplayed()
        composeTestRule.onNodeWithText("By").assertIsDisplayed()
        composeTestRule.onNodeWithText(photoAlbum.userName).assertIsDisplayed()
        composeTestRule.onNodeWithText("Titled").assertIsDisplayed()
        composeTestRule.onNodeWithText(photoAlbum.photoTitle).assertIsDisplayed()
    }
}