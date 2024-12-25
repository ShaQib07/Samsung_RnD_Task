package com.shakib.samsungrndtask.presentation.views.screens.photoAlbumList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakib.samsungrndtask.domain.model.PhotoAlbumModel
import com.shakib.samsungrndtask.domain.model.ResponseState
import com.shakib.samsungrndtask.domain.usecase.ObservePhotoAlbumsUseCase
import com.shakib.samsungrndtask.presentation.model.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoAlbumsViewModel @Inject constructor(
    private val observePhotoAlbumsUseCase: ObservePhotoAlbumsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PhotoAlbumListScreenState())
    val state = _state.asStateFlow()

    init {
        getPhotoAlbums()
    }

    private fun getPhotoAlbums() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            observePhotoAlbumsUseCase().collectLatest { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> _state.update { it.copy(isLoading = true) }
                    is ResponseState.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            error = responseState.throwable.message.toString()
                        )
                    }

                    is ResponseState.Success<List<PhotoAlbumModel>> -> _state.update {
                        it.copy(
                            isLoading = false,
                            photoAlbums = responseState.data.map { photoAlbumModel -> photoAlbumModel.toUIModel() })
                    }
                }
            }
        }
    }
}
