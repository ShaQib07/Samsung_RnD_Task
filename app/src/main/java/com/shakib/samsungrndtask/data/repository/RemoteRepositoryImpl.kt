package com.shakib.samsungrndtask.data.repository

import com.shakib.samsungrndtask.data.dataSource.remote.Api
import com.shakib.samsungrndtask.data.model.AlbumDTO
import com.shakib.samsungrndtask.data.model.PhotoDTO
import com.shakib.samsungrndtask.data.model.UserDTO
import com.shakib.samsungrndtask.data.model.toDomainModel
import com.shakib.samsungrndtask.di.IoDispatcher
import com.shakib.samsungrndtask.domain.model.PhotoAlbumModel
import com.shakib.samsungrndtask.domain.model.ResponseState
import com.shakib.samsungrndtask.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
    Repository implementation in the data layer that uses the remote data source to
    keep the implementation details abstract from the domain layer.
*/
class RemoteRepositoryImpl @Inject constructor(
    private val api: Api,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : Repository {
    override val photoAlbumList: MutableStateFlow<ResponseState<List<PhotoAlbumModel>>> =
        MutableStateFlow(ResponseState.Loading)

    override suspend fun fetchAndUpdatePhotoAlbumList() {
        val result = runCatching { fetchData() }
        result.onSuccess { photoAlbumList.emit(ResponseState.Success(it)) }
        result.onFailure { photoAlbumList.emit(ResponseState.Error(it)) }
    }

    private suspend fun fetchData() = withContext(ioDispatcher) {
        val results = awaitAll(
            async { api.getPhotos() },
            async { api.getAlbums() },
            async { api.getUsers() }
        )

        val userMap = (results[2] as List<UserDTO>).associateBy { it.id }
        val photoMap = (results[0] as List<PhotoDTO>).groupBy { it.albumId }
        val photoAlbums = (results[1] as List<AlbumDTO>).map { album ->
            PhotoAlbumModel(
                id = album.id,
                title = album.title,
                user = userMap[album.userId]?.toDomainModel(),
                photos = photoMap[album.id]?.map { it.toDomainModel() }
            )
        }

        photoAlbums
    }
}
