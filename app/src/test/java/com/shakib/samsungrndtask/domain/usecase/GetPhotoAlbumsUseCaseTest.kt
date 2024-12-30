package com.shakib.samsungrndtask.domain.usecase

import com.shakib.samsungrndtask.domain.repository.Repository
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetPhotoAlbumsUseCaseTest {

    private val repository = mockk<Repository>(relaxed = true)
    private lateinit var getPhotoAlbumsUseCase: GetPhotoAlbumsUseCase

    @Before
    fun setup() {
        getPhotoAlbumsUseCase = GetPhotoAlbumsUseCase(repository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `invoke should call fetchAndUpdatePhotoAlbumList`() = runTest {
        // Call the use case
        getPhotoAlbumsUseCase()

        // Verify that the repository method was called
        coVerify(exactly = 1) { repository.fetchAndUpdatePhotoAlbumList() }
    }
}