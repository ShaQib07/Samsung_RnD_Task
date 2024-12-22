package com.shakib.samsungrndtask.domain.model

// Domain layer model to keep the domain layer separate from other layers
data class AlbumModel(
    val id: Int,
    val title: String,
    val userId: Int
)
