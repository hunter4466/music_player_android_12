package com.ravnnerdery.music_player_android_12.application

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.ravnnerdery.music_player_android_12.services.AlarmReceiver
import com.ravnnerdery.music_player_android_12.services.CustomMediaService
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
import com.ravnnerdery.music_player_android_12.ui.application.Application
import com.ravnnerdery.music_player_android_12.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
) : AppCompatActivity() {

    @Inject
    lateinit var musicPlayer: MusicPlayer

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var alarmManager: AlarmManager

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun triggerMediaPlayer(keyword: String, id: String = "none") {
            val mediaIntent = Intent(this, CustomMediaService::class.java).also {
                it.putExtra("action", keyword)
                it.putExtra("id", id)
            }
            applicationContext.startForegroundService(mediaIntent)
        }

        fun playTrack(id: String) {
            triggerMediaPlayer("play_track", id)
        }

        fun playNextTrack() {
            triggerMediaPlayer("play_next")
        }

        fun playPreviousTrack() {
            triggerMediaPlayer("play_previous")
        }

        fun launchService() {
            Log.wtf("BUGCHASE", "executed launch funciton in activity")
            triggerMediaPlayer("launch_service")
        }

        fun setAlarm(time: Int, id: String) {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, AlarmReceiver::class.java)
                .putExtra("id", id)
            val pendingIntent =
                PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            alarmManager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + time,
                pendingIntent
            )
            Toast.makeText(this, "Alarm Set,", Toast.LENGTH_SHORT).show()
        }

        fun cancelAlarm() {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            alarmManager.cancel(pendingIntent)
            Toast.makeText(this, "Alarm Cancelled,", Toast.LENGTH_SHORT).show()
        }

        val imgLoader = ImageLoader.invoke(this).newBuilder()
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder(this@MainActivity))
                } else {
                    add(GifDecoder())
                }
            }.build()
        launchService()
        setContent {
            ApplicationTheme {
                Scaffold {
                    Application(
                        viewModel = mainViewModel,
                        imageLoader = imgLoader,
                        musicPlayer = musicPlayer,
                        onNextTrackClick = { playNextTrack() },
                        onPreviousTrackClick = { playPreviousTrack() },
                        onPlayPause = { id -> playTrack(id) },
                        setAlarm = { time, id ->
                            cancelAlarm()
                            setAlarm(time, id)
                        },

                        )
                }
            }
        }
    }
}