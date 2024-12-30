package com.shakib.samsungrndtask.domain.usecase

import com.shakib.samsungrndtask.domain.repository.Repository
import javax.inject.Inject

// Use case for fetching data from the repository
class GetPhotoAlbumsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.fetchAndUpdatePhotoAlbumList()
}
