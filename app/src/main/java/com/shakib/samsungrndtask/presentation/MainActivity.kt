package com.shakib.samsungrndtask.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.shakib.samsungrndtask.BuildConfig
import com.shakib.samsungrndtask.domain.usecase.GetPhotoAlbumsUseCase
import com.shakib.samsungrndtask.presentation.theme.SamsungRDTaskTheme
import com.shakib.samsungrndtask.presentation.views.screens.photoAlbumList.PhotoAlbumListScreen
import com.shakib.samsungrndtask.service.DataFetchIntentService
import com.shakib.samsungrndtask.service.DataFetchWorker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workManager: WorkManager

    @Inject
    lateinit var constraints: Constraints

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (BuildConfig.USE_WORK_MANAGER) {
            fetchDataWithWorkManager()
        } else {
            fetchDataWithService()
        }

        setContent {
            SamsungRDTaskTheme {
                PhotoAlbumListScreen()
            }
        }
    }

    private fun fetchDataWithService() {
        val intent = Intent(this@MainActivity, DataFetchIntentService::class.java)
        startService(intent)
    }

    private fun fetchDataWithWorkManager() {
        val work = OneTimeWorkRequestBuilder<DataFetchWorker>()
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniqueWork(
            "dataFetch",
            ExistingWorkPolicy.KEEP,
            work
        )
    }
}
