package com.shakib.samsungrndtask.data.repository

import com.shakib.samsungrndtask.data.dataSource.remote.Api
import com.shakib.samsungrndtask.data.model.AlbumDTO
import com.shakib.samsungrndtask.data.model.PhotoDTO
import com.shakib.samsungrndtask.data.model.UserDTO
import com.shakib.samsungrndtask.data.model.toDomainModel
import com.shakib.samsungrndtask.domain.model.AlbumModel
import com.shakib.samsungrndtask.domain.model.PhotoModel
import com.shakib.samsungrndtask.domain.model.UserModel
import com.shakib.samsungrndtask.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
    Repository implementation in data layer that uses the remote data source to
    keep the implementation details abstract from domain layer.
*/
class RemoteRepositoryImpl @Inject constructor(private val api: Api) : Repository {
    override suspend fun getPhotos(): List<PhotoModel> =
        withContext(Dispatchers.IO) { api.getPhotos().map(PhotoDTO::toDomainModel) }

    override suspend fun getAlbums(): List<AlbumModel> =
        withContext(Dispatchers.IO) { api.getAlbums().map(AlbumDTO::toDomainModel) }

    override suspend fun getUsers(): List<UserModel> =
        withContext(Dispatchers.IO) { api.getUsers().map(UserDTO::toDomainModel) }
}
