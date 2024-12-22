package com.shakib.samsungrndtask.data.model

import com.shakib.samsungrndtask.domain.model.PhotoModel

// Data layer model to parse API response
data class PhotoDTO(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

// Use this function to map data layer model to domain layer model
fun PhotoDTO.toDomainModel() = PhotoModel(
    albumId = albumId,
    id = id,
    thumbnailUrl = thumbnailUrl,
    title = title,
    url = url
)
