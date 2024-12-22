package com.shakib.samsungrndtask.domain.repository

import com.shakib.samsungrndtask.domain.model.AlbumModel
import com.shakib.samsungrndtask.domain.model.PhotoModel
import com.shakib.samsungrndtask.domain.model.UserModel

interface Repository {
    suspend fun getPhotos(): List<PhotoModel>

    suspend fun getAlbums(): List<AlbumModel>

    suspend fun getUsers(): List<UserModel>
}
