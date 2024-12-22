package com.shakib.samsungrndtask.data.dataSource.remote

import com.shakib.samsungrndtask.data.model.AlbumDTO
import com.shakib.samsungrndtask.data.model.PhotoDTO
import com.shakib.samsungrndtask.data.model.UserDTO
import retrofit2.http.GET

interface Api {
    @GET("albums")
    suspend fun getAlbums(): List<AlbumDTO>

    @GET("users")
    suspend fun getUsers(): List<UserDTO>

    @GET("photos")
    suspend fun getPhotos(): List<PhotoDTO>
}
