package com.shakib.samsungrndtask.domain.model

// Domain layer model to keep the domain layer separate from other layers
data class PhotoAlbumModel(
    val id: Int,
    val title: String,
    val user: UserModel?,
    val photos: List<PhotoModel>?
)
