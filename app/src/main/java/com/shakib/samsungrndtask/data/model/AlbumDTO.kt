package com.shakib.samsungrndtask.data.model

import com.shakib.samsungrndtask.domain.model.AlbumModel

// Data layer model to parse API response
data class AlbumDTO(
    val id: Int,
    val title: String,
    val userId: Int
)

// Use this function to map data layer model to domain layer model
fun AlbumDTO.toDomainModel() = AlbumModel(
    id = id,
    title = title,
    userId = userId
)
