package com.shakib.samsungrndtask.service

import android.app.IntentService
import com.shakib.samsungrndtask.di.qualifier.IoDispatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

// A base class for coroutine-based intent services
@AndroidEntryPoint
abstract class CoroutineIntentService(name: String) : IntentService(name) {

    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

    // Define the coroutine scope for this service
    private val serviceJob = SupervisorJob()
    private val coroutineScope by lazy { CoroutineScope(ioDispatcher + serviceJob) }

    // Use the scope to launch coroutines
    protected fun serviceScope(block: suspend CoroutineScope.() -> Unit) {
        coroutineScope.launch(block = block)
    }
}
