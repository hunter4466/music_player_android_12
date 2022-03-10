package com.ravnnerdery.music_player_android_12.services

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicService : Service() {

    var TAG = "MyService"
    var isRunning = false

    init {
        isRunning = true
        Thread {
            while (isRunning){
                Log.wtf("MARIOCH","Service is running... with: $TAG")
                Thread.sleep(1000)
            }
        }.start()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("Extra_data")
        dataString?.let {
            TAG = it
        }
        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        Log.wtf("MARIOCH","Service is destroyed")
    }
}

