package com.shakib.samsungrndtask.presentation.model

import com.shakib.samsungrndtask.domain.model.PhotoAlbumModel

// Presentation layer model
data class PhotoAlbum(
    val id: Int,
    val albumName: String,
    val userName: String,
    val photoTitle: String,
    val photoThumbnailUrl: String
)

/*
    Use this function to map domain layer model to presentation layer model.
    This function is not being kept with domain layer model so that
    we don't violate clean architecture principle by injecting presentation layer dependency.
*/
fun PhotoAlbumModel.toUIModel() = PhotoAlbum(
    id = id,
    albumName = title,
    userName = user?.name ?: "",
    photoTitle = photos?.firstOrNull()?.title ?: "",
    photoThumbnailUrl = photos?.firstOrNull()?.thumbnailUrl ?: ""
)
