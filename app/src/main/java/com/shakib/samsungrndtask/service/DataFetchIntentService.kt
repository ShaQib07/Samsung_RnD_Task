package com.shakib.samsungrndtask.service

import android.content.Intent
import android.util.Log
import com.shakib.samsungrndtask.domain.usecase.GetPhotoAlbumsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Intent service responsible for fetching data from the network
@AndroidEntryPoint
class DataFetchIntentService : CoroutineIntentService("NetworkIntentService") {

    @Inject
    lateinit var getPhotoAlbumsUseCase: GetPhotoAlbumsUseCase

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        Log.d("SRnD", "Fetching data using SERVICE")
        serviceScope {
            getPhotoAlbumsUseCase()
        }
    }
}
