package com.shakib.samsungrndtask.service

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.shakib.samsungrndtask.domain.usecase.GetPhotoAlbumsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

// Coroutine worker responsible for fetching data from the network
@HiltWorker
class DataFetchWorker @AssistedInject constructor(
    private val getPhotoAlbumsUseCase: GetPhotoAlbumsUseCase,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("SRnD", "Fetching data using WORK MANAGER")
        getPhotoAlbumsUseCase()
        return Result.success()
    }
}
