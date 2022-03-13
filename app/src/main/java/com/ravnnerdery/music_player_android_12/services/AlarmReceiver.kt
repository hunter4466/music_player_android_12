package com.ravnnerdery.music_player_android_12.services

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ravnnerdery.data.useCases.ProvideTracksFlowUseCase
import com.ravnnerdery.domain.other.Constants.ALARM_CHANNEL_ID
import com.ravnnerdery.music_player_android_12.application.AlarmActivity
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var provideTracksFlowUseCase: ProvideTracksFlowUseCase

    @Inject
    lateinit var musicPlayer: MusicPlayer

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private fun feedAndStart(id: String) {
        uiScope.launch {
            return@launch provideTracksFlowUseCase.execute().collect { list ->
                if (list.isNotEmpty()) {
                    musicPlayer.feedMusicList(list)
                    musicPlayer.playPauseSong(id)
                }
            }
        }
    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.getStringExtra("id")
        feedAndStart(id ?: "backinblack")
        val int = Intent(context, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingItem = PendingIntent.getActivity(context, 0, int, PendingIntent.FLAG_MUTABLE)
        val builder = NotificationCompat.Builder(context, ALARM_CHANNEL_ID)
            .setSmallIcon(coil.gif.R.drawable.notification_action_background)
            .setContentTitle("Alarm")
            .setContentText("Time to wake up!")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingItem)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123, builder.build())
    }
}