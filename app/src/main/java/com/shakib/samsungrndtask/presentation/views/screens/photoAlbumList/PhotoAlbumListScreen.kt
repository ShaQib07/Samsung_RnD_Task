package com.shakib.samsungrndtask.presentation.views.screens.photoAlbumList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shakib.samsungrndtask.R
import com.shakib.samsungrndtask.presentation.theme.SamsungRDTaskTheme
import com.shakib.samsungrndtask.presentation.views.components.EmptyView
import com.shakib.samsungrndtask.presentation.views.components.ErrorView

@Composable
fun PhotoAlbumListScreen(
    viewModel: PhotoAlbumsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PhotoAlbumListView(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PhotoAlbumListView(state: PhotoAlbumListScreenState) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.photo_albums),
                    fontWeight = FontWeight.Bold
                )
            })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (state.isEmpty)
                EmptyView()
            else
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp)
                ) {
                    items(state.photoAlbums) { photoAlbum ->
                        PhotoAlbumListItemView(photoAlbum = photoAlbum)
                    }
                }

            if (state.isLoading)
                CircularProgressIndicator(modifier = Modifier.testTag("loading_indicator"))

            if (state.error.isNotEmpty())
                ErrorView(modifier = Modifier.padding(20.dp), message = state.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoAlbumListPreview() {
    SamsungRDTaskTheme {
        PhotoAlbumListView(state = PhotoAlbumListScreenState())
    }
}
