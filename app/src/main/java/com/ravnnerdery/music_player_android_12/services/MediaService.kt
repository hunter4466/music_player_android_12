package com.ravnnerdery.music_player_android_12.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.ravnnerdery.data.useCases.ProvideTracksFlowUseCase
import com.ravnnerdery.domain.models.Track
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MediaService: Service() {

    @Inject
    lateinit var provideTracksFlowUseCase: ProvideTracksFlowUseCase

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var TAG = "MyService"
    private var isRunning = false
    private var trackList = emptyList<Track>()
    private fun bringList() {
        uiScope.launch {
            return@launch provideTracksFlowUseCase.execute().collect { list ->
                trackList = list.map { it }
            }
        }
    }

    init {
        isRunning = true
        Thread {
            while (isRunning){
                bringList()
                Log.wtf("MARIOCH","Service is running... with: $TAG")
                Log.wtf("MARIOCH", "The list contains ${trackList.size} elements.")
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
        return Service.START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        Log.wtf("MARIOCH","Service is destroyed")
    }
}
