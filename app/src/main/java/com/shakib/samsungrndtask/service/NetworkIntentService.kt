package com.shakib.samsungrndtask.service

import android.content.Intent
import com.shakib.samsungrndtask.domain.usecase.GetPhotoAlbumsUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Intent service responsible for fetching data from the network
@AndroidEntryPoint
class NetworkIntentService : CoroutineIntentService("NetworkIntentService") {

    @Inject
    lateinit var getPhotoAlbumsUseCase: GetPhotoAlbumsUseCase

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        serviceScope {
            getPhotoAlbumsUseCase()
        }
    }
}
