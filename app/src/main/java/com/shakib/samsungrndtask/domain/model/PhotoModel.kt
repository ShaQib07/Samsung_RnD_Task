package com.shakib.samsungrndtask.domain.model

// Domain layer model to keep the domain layer separate from other layers
data class PhotoModel(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)
