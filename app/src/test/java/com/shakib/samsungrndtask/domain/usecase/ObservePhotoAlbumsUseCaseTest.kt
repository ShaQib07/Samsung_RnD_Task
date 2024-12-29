package com.shakib.samsungrndtask.domain.usecase

import com.shakib.samsungrndtask.domain.model.PhotoAlbumModel
import com.shakib.samsungrndtask.domain.model.ResponseState
import com.shakib.samsungrndtask.domain.repository.Repository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ObservePhotoAlbumsUseCaseTest {

    private val repository = mockk<Repository>(relaxed = true)
    private lateinit var observePhotoAlbumsUseCase: ObservePhotoAlbumsUseCase

    @Before
    fun setup() {
        observePhotoAlbumsUseCase = ObservePhotoAlbumsUseCase(repository)
    }

    @Test
    fun `invoke should return loading state`() = runTest {
        // Arrange
        val mockFlow: MutableStateFlow<ResponseState<List<PhotoAlbumModel>>> =
            MutableStateFlow(ResponseState.Loading)
        every { repository.photoAlbumList } returns mockFlow

        // Act
        val result = observePhotoAlbumsUseCase()

        // Assert
        assertEquals(mockFlow, result)

        val firstValue = result.first()
        assertEquals(ResponseState.Loading, firstValue)
    }

    @Test
    fun `invoke should return success state`() = runTest {
        // Arrange
        val photoAlbumList = mockk<List<PhotoAlbumModel>>()
        val mockFlow: MutableStateFlow<ResponseState<List<PhotoAlbumModel>>> =
            MutableStateFlow(ResponseState.Success(photoAlbumList))
        every { repository.photoAlbumList } returns mockFlow

        // Act
        val result = observePhotoAlbumsUseCase()

        // Assert
        assertEquals(mockFlow, result)

        val firstValue = result.first()
        assertEquals(ResponseState.Success(photoAlbumList), firstValue)
    }

    @Test
    fun `invoke should return error state`() = runTest {
        // Arrange
        val throwable = mockk<Throwable>()
        val mockFlow: MutableStateFlow<ResponseState<List<PhotoAlbumModel>>> =
            MutableStateFlow(ResponseState.Error(throwable))
        every { repository.photoAlbumList } returns mockFlow

        // Act
        val result = observePhotoAlbumsUseCase()

        // Assert
        assertEquals(mockFlow, result)

        val firstValue = result.first()
        assertEquals(ResponseState.Error(throwable), firstValue)
    }
}
