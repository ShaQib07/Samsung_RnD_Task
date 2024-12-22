package com.shakib.samsungrndtask.presentation.views.screens.photoAlbumList

import com.shakib.samsungrndtask.presentation.model.PhotoAlbum

data class PhotoAlbumListScreenState(
    val photoAlbums: List<PhotoAlbum> = emptyList(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String = ""
)
