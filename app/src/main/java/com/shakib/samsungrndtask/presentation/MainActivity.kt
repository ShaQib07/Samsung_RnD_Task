package com.shakib.samsungrndtask.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shakib.samsungrndtask.presentation.theme.SamsungRDTaskTheme
import com.shakib.samsungrndtask.presentation.views.screens.photoAlbumList.PhotoAlbumListScreen
import com.shakib.samsungrndtask.service.NetworkIntentService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val intent = Intent(this@MainActivity, NetworkIntentService::class.java)
        startService(intent)

        setContent {
            SamsungRDTaskTheme {
                PhotoAlbumListScreen()
            }
        }
    }
}
