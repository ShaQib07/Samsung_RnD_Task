package com.shakib.samsungrndtask.presentation.views.screens.photoAlbumList

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shakib.samsungrndtask.presentation.model.PhotoAlbum
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotoAlbumListViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun photoAlbumListView_displaysLoadingIndicator_whenStateIsLoading() {
        // Given: A state where data is being loaded
        val state = PhotoAlbumListScreenState(isLoading = true)

        // When: The composable is displayed
        composeTestRule.setContent {
            PhotoAlbumListView(state = state)
        }

        // Then: Verify the loading indicator is displayed
        composeTestRule.onNodeWithTag("loading_indicator").assertIsDisplayed()
    }

    @Test
    fun photoAlbumListView_displaysEmptyView_whenStateIsEmpty() {
        // Given: A state where no photo albums are available
        val state = PhotoAlbumListScreenState(isEmpty = true, isLoading = false)

        // When: The composable is displayed
        composeTestRule.setContent {
            PhotoAlbumListView(state = state)
        }

        // Then: Verify the EmptyView is displayed
        composeTestRule.onNodeWithText("Sorry! No item found").assertIsDisplayed()
    }

    @Test
    fun photoAlbumListView_displaysErrorView_whenStateHasError() {
        // Given: A state where an error occurred
        val state = PhotoAlbumListScreenState(
            isLoading = false,
            error = "Oops! Something went wrong"
        )

        // When: The composable is displayed
        composeTestRule.setContent {
            PhotoAlbumListView(state = state)
        }

        // Then: Verify the error message is displayed
        composeTestRule.onNodeWithText(state.error).assertIsDisplayed()
    }

    @Test
    fun photoAlbumListView_displaysPhotoAlbums_whenStateHasData() {
        // Given: A state with photo albums
        val photoAlbums = listOf(
            PhotoAlbum(1, "Album 1", "User 1", "Title 1", ""),
            PhotoAlbum(2, "Album 2", "User 2", "Title 2", "")
        )
        val state = PhotoAlbumListScreenState(
            isEmpty = false,
            isLoading = false,
            error = "",
            photoAlbums = photoAlbums
        )

        // When: The composable is displayed
        composeTestRule.setContent {
            PhotoAlbumListView(state = state)
        }

        // Then: Verify the photo albums are displayed
        composeTestRule.onNodeWithContentDescription("Title 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Album 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("User 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Title 1").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Title 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Album 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("User 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Title 2").assertIsDisplayed()
    }
}