package com.shakib.samsungrndtask.presentation.views.screens.photoAlbumList

import com.shakib.samsungrndtask.domain.model.PhotoAlbumModel
import com.shakib.samsungrndtask.domain.model.ResponseState
import com.shakib.samsungrndtask.domain.usecase.ObservePhotoAlbumsUseCase
import com.shakib.samsungrndtask.presentation.model.PhotoAlbum
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoAlbumsViewModelTest {

    private val observePhotoAlbumsUseCase = mockk<ObservePhotoAlbumsUseCase>()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: PhotoAlbumsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `init should call loadPhotoAlbums and update state to loading`() = runTest {
        // Arrange
        val fakeLoadingFlow = getFakeResponseState(State.LOADING)
        val fakeSuccessFlow = getFakeResponseState(State.SUCCESS)

        coEvery { observePhotoAlbumsUseCase() } coAnswers {
            fakeLoadingFlow
        } coAndThen {
            delay(1_000)
            fakeSuccessFlow
        }

        // Act
        viewModel = PhotoAlbumsViewModel(observePhotoAlbumsUseCase)
        runCurrent()

        // Assert
        assertEquals(true, viewModel.state.value.isLoading)
        assertEquals(emptyList<PhotoAlbum>(), viewModel.state.value.photoAlbums)
    }

    @Test
    fun `init should call loadPhotoAlbums and update state to success`() = runTest {
        // Arrange
        val fakeSuccessFlow = getFakeResponseState(State.SUCCESS)

        coEvery { observePhotoAlbumsUseCase() } returns fakeSuccessFlow

        // Act
        viewModel = PhotoAlbumsViewModel(observePhotoAlbumsUseCase)
        runCurrent()

        // Assert
        assertEquals(1, viewModel.state.value.photoAlbums.size)
        assertEquals("Album Title", viewModel.state.value.photoAlbums.first().albumName)
    }

    @Test
    fun `init should call loadPhotoAlbums and update state to error`() = runTest {
        // Arrange
        val fakeErrorFlow = getFakeResponseState(State.ERROR)

        coEvery { observePhotoAlbumsUseCase() } returns fakeErrorFlow

        // Act
        viewModel = PhotoAlbumsViewModel(observePhotoAlbumsUseCase)
        runCurrent()

        // Assert
        assertEquals("Error", viewModel.state.value.error)
        assertEquals(emptyList<PhotoAlbum>(), viewModel.state.value.photoAlbums)
    }

    // Helper functions
    private fun getFakeResponseState(state: State): MutableStateFlow<ResponseState<List<PhotoAlbumModel>>> =
        when (state) {
            State.LOADING -> MutableStateFlow(ResponseState.Loading)
            State.SUCCESS -> MutableStateFlow(ResponseState.Success(getFakePhotoAlbumList()))
            State.ERROR -> MutableStateFlow(ResponseState.Error(Throwable("Error")))
        }

    private fun getFakePhotoAlbumList() =
        listOf(PhotoAlbumModel(id = 1, title = "Album Title", user = null, photos = null))
}

private enum class State {
    LOADING, SUCCESS, ERROR
}