package com.ravnnerdery.music_player_android_12.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.media.app.NotificationCompat as MediaNotificationCompat
import com.ravnnerdery.data.useCases.*
import com.ravnnerdery.domain.other.Constants.MEDIA_CHANNEL_ID
import com.ravnnerdery.music_player_android_12.R
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class CustomMediaService : Service() {

    @Inject
    lateinit var provideTracksFlowUseCase: ProvideTracksFlowUseCase

    @Inject
    lateinit var changeLoadingStateUseCase: ChangeLoadingStateUseCase

    @Inject
    lateinit var musicPlayer: MusicPlayer

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        uiScope.launch {
            provideTracksFlowUseCase.execute().collect { list ->
                if (list.isNotEmpty()) {
                    musicPlayer.feedMusicList(list)
                    changeLoadingStateUseCase.execute(false)
                }
            }
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.S)
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
            "launch_service" -> {

            }
        }
        try {
            val playNextIntent = Intent(this, NotificationMediaService::class.java).also {
                it.putExtra("action", "play_next")
            }
            val playOrPauseIntent = Intent(this, NotificationMediaService::class.java).also {
                it.putExtra("action", "play_track")
            }
            val playPreviousIntent = Intent(this, NotificationMediaService::class.java).also {
                it.putExtra("action", "play_previous")
            }
            val playNextPendingIntent: PendingIntent = PendingIntent.getService(
                this@CustomMediaService,
                11,
                playNextIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            val playOrPausePendingIntent: PendingIntent = PendingIntent.getService(
                this@CustomMediaService,
                22,
                playOrPauseIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            val playPreviousPendingIntent: PendingIntent = PendingIntent.getService(
                this@CustomMediaService,
                33,
                playPreviousIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val mediaNotification = NotificationCompat.Builder(this, MEDIA_CHANNEL_ID)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.play_btn_asset)
                .addAction(
                    R.drawable.play_previous_btn_asset,
                    "Previous",
                    playPreviousPendingIntent
                )
                .addAction(R.drawable.play_btn_asset, "Pause", playOrPausePendingIntent)
                .addAction(R.drawable.play_next_btn_asset, "Next", playNextPendingIntent)
                .setStyle(
                    MediaNotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2)
                )
                .setContentTitle("Playing media...")
                .build()
            startForeground(1, mediaNotification)
        } catch (e: Exception) {
            Log.wtf("Exception", "$e")
        }

        return START_STICKY
    }

}
