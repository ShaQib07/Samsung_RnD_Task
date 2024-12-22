package com.shakib.samsungrndtask.domain.usecase

import com.shakib.samsungrndtask.domain.model.AlbumModel
import com.shakib.samsungrndtask.domain.model.PhotoAlbumModel
import com.shakib.samsungrndtask.domain.model.PhotoModel
import com.shakib.samsungrndtask.domain.model.UserModel
import com.shakib.samsungrndtask.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPhotoAlbumsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        val result = awaitAll(
            async { repository.getPhotos() },
            async { repository.getAlbums() },
            async { repository.getUsers() }
        )

        val userMap = (result[2] as List<UserModel>).associateBy { it.id }
        val photoMap = (result[0] as List<PhotoModel>).groupBy { it.albumId }
        val photoAlbums = (result[1] as List<AlbumModel>).map {
            PhotoAlbumModel(
                id = it.id,
                title = it.title,
                user = userMap[it.userId],
                photos = photoMap[it.id]
            )
        }

        return@withContext photoAlbums
    }
}
