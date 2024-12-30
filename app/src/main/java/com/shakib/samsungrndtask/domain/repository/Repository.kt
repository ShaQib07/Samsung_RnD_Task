package com.shakib.samsungrndtask.domain.repository

import com.shakib.samsungrndtask.domain.model.PhotoAlbumModel
import com.shakib.samsungrndtask.domain.model.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow

interface Repository {
    val photoAlbumList: MutableStateFlow<ResponseState<List<PhotoAlbumModel>>>

    suspend fun fetchAndUpdatePhotoAlbumList()
}
