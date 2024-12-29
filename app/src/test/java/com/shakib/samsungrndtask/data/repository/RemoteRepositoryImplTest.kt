package com.shakib.samsungrndtask.data.repository

import com.shakib.samsungrndtask.data.dataSource.remote.Api
import com.shakib.samsungrndtask.data.model.AddressDTO
import com.shakib.samsungrndtask.data.model.AlbumDTO
import com.shakib.samsungrndtask.data.model.CompanyDTO
import com.shakib.samsungrndtask.data.model.GeoDTO
import com.shakib.samsungrndtask.data.model.PhotoDTO
import com.shakib.samsungrndtask.data.model.UserDTO
import com.shakib.samsungrndtask.domain.model.PhotoAlbumModel
import com.shakib.samsungrndtask.domain.model.ResponseState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteRepositoryImplTest {

    private val api = mockk<Api>()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: RemoteRepositoryImpl

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = RemoteRepositoryImpl(api, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchAndUpdatePhotoAlbumList should emit success when API calls succeed`() = runTest {
        // Arrange
        val mockPhotos = listOf(getFakePhoto())
        val mockAlbums = listOf(getFakeAlbum())
        val mockUsers = listOf(getFakeUser())

        coEvery { api.getPhotos() } returns mockPhotos
        coEvery { api.getAlbums() } returns mockAlbums
        coEvery { api.getUsers() } returns mockUsers

        val resultFlow = repository.photoAlbumList
        val collectedStates = mutableListOf<ResponseState<List<PhotoAlbumModel>>>()
        val job = launch {
            resultFlow.collect { collectedStates.add(it) }
        }

        // Act
        repository.fetchAndUpdatePhotoAlbumList()

        advanceUntilIdle()

        // Assert
        assertEquals(2, collectedStates.size)
        assert(collectedStates[0] is ResponseState.Loading)
        assert(collectedStates[1] is ResponseState.Success)

        val successState = collectedStates[1] as ResponseState.Success
        assertEquals(1, successState.data.size) // One album
        assertEquals("Album Title", successState.data[0].title) // Album title

        job.cancel()
    }

    @Test
    fun `fetchAndUpdatePhotoAlbumList should emit error when API calls fail`() = runTest {
        // Arrange
        coEvery { api.getPhotos() } throws Exception("Failed to fetch photos")
        coEvery { api.getAlbums() } throws Exception("Failed to fetch albums")
        coEvery { api.getUsers() } throws Exception("Failed to fetch users")

        val resultFlow = repository.photoAlbumList
        val collectedStates = mutableListOf<ResponseState<List<PhotoAlbumModel>>>()
        val job = launch {
            resultFlow.collect { collectedStates.add(it) }
        }

        // Act
        repository.fetchAndUpdatePhotoAlbumList()

        advanceUntilIdle()

        // Assert
        assertEquals(2, collectedStates.size)
        assert(collectedStates[0] is ResponseState.Loading)
        assert(collectedStates[1] is ResponseState.Error)

        val errorState = collectedStates[1] as ResponseState.Error
        assert(errorState.throwable.message.toString().contains("Failed to fetch"))

        job.cancel()
    }

    // Helper functions
    private fun getFakePhoto() = PhotoDTO(1, 1, "Photo Title", "url", "thumbnailUrl")

    private fun getFakeAlbum() = AlbumDTO(1, "Album Title", 1)

    private fun getFakeUser() = UserDTO(
        1,
        "User Name",
        "username",
        "email",
        AddressDTO("street", "suite", "city", "zipcode", GeoDTO("lat", "lng")),
        "phone",
        "website",
        CompanyDTO("name", "catchPhrase", "bs")
    )
}