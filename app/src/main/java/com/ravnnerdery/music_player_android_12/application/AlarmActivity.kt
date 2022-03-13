package com.ravnnerdery.music_player_android_12.application

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.material.internal.ContextUtils.getActivity
import com.ravnnerdery.music_player_android_12.services.AlarmReceiver
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
import com.ravnnerdery.music_player_android_12.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmActivity @Inject constructor(
) : AppCompatActivity() {

    @Inject
    lateinit var musicPlayer: MusicPlayer

    private lateinit var alarmManager: AlarmManager

    @RequiresApi(Build.VERSION_CODES.S)
    fun cancelAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.cancel(pendingIntent)
        Toast.makeText(this, "Alarm Cancelled,", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                Scaffold(
                    backgroundColor = MaterialTheme.colors.primaryVariant
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        val (card) = createRefs()
                        Card(
                            modifier = Modifier
                                .constrainAs(card) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            elevation = 16.dp,
                        ) {
                            Column(
                                modifier = Modifier.padding(32.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "Time to wake up!...",
                                    style = MaterialTheme.typography.h5
                                )
                                Button(
                                    onClick = {
                                        cancelAlarm()
                                        musicPlayer.stopCurrent()
                                        getActivity(this@AlarmActivity)?.onBackPressed()
                                    }
                                ) {
                                    Text(
                                        text = "OK!",
                                        style = MaterialTheme.typography.h6
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}