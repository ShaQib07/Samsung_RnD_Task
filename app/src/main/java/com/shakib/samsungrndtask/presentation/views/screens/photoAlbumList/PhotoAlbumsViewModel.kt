package com.shakib.samsungrndtask.presentation.views.screens.photoAlbumList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakib.samsungrndtask.domain.usecase.GetPhotoAlbumsUseCase
import com.shakib.samsungrndtask.presentation.model.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoAlbumsViewModel @Inject constructor(
    private val getPhotoAlbumsUseCase: GetPhotoAlbumsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PhotoAlbumListScreenState())
    val state = _state.asStateFlow()

    init {
        getPhotoAlbums()
    }

    private fun getPhotoAlbums() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = runCatching { getPhotoAlbumsUseCase() }
            result.onSuccess { photoAlbums ->
                _state.update {
                    it.copy(
                        photoAlbums = photoAlbums.map { photoAlbumModel -> photoAlbumModel.toUIModel() },
                        isEmpty = photoAlbums.isEmpty()
                    )
                }
            }
            result.onFailure { exception -> _state.update { it.copy(error = exception.message.toString()) } }
            _state.update { it.copy(isLoading = false) }
        }
    }
}
