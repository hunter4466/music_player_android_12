package com.ravnnerdery.music_player_android_12.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationMediaService : Service() {

    @Inject
    lateinit var musicPlayer: MusicPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.getStringExtra("action")) {
            "play_track" -> {
                Log.wtf("BUGCHASES", "PLAY_TRACK")
                musicPlayer.playPauseSong(musicPlayer.getCurrentSongId())
            }
            "play_next" -> {
                Log.wtf("BUGCHASES", "PLAY_NEXT")
                musicPlayer.playNext()
            }
            "play_previous" -> {
                Log.wtf("BUGCHASES", "PLAY_PREVIOUS")
                musicPlayer.playPrevious()
            }
        }
        return START_STICKY
    }
}