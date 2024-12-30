package com.shakib.samsungrndtask.domain.usecase

import com.shakib.samsungrndtask.domain.repository.Repository
import javax.inject.Inject

// Use case for observing the changes in data in the repository
class ObservePhotoAlbumsUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke() = repository.photoAlbumList
}
