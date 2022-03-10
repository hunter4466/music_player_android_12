package com.ravnnerdery.music_player_android_12.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.ravnnerdery.data.useCases.ProvideTracksFlowUseCase
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
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

    @Inject
    lateinit var musicPlayer: MusicPlayer

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var isRunning = false

    init {
        uiScope.launch {
            return@launch provideTracksFlowUseCase.execute().collect { list ->
                musicPlayer.feedMusicList(list.map { it })
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.getStringExtra("action")
        val id = intent?.getStringExtra("id")
        when (action) {
            "play_track" -> {
                musicPlayer.playPauseSong(id ?: "0")
            }
            "play_next" -> {
                musicPlayer.playNext()
            }
            "play_previous" -> {
                musicPlayer.playPrevious()
            }
            "load_first" -> {

            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        Log.wtf("MARIOCH","Service is destroyed")
    }
}
