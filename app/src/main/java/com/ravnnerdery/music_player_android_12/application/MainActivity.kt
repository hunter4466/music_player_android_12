package com.ravnnerdery.music_player_android_12.application

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.ravnnerdery.music_player_android_12.R
import com.ravnnerdery.music_player_android_12.services.MediaService
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
import com.ravnnerdery.music_player_android_12.ui.application.Application
import com.ravnnerdery.starwarschallenge.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {

    @Inject
    lateinit var musicPlayer: MusicPlayer

    init {
        triggerMediaPlayer("load_first")
    }

    private fun triggerMediaPlayer(keyword: String, id: String = "none") {
        Intent(this, MediaService::class.java).also {
            it.putExtra("action",keyword)
            it.putExtra("id",id)
            startService(it)
        }
    }

    private fun playTrack(id: String) {
        triggerMediaPlayer("play_track", id)
    }

    private fun playNextTrack() {
        triggerMediaPlayer("play_next")
    }

    private fun playPreviousTrack() {
        triggerMediaPlayer("play_previous")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imgLoader = ImageLoader.invoke(this).newBuilder()
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder(this@MainActivity))
                } else {
                    add(GifDecoder())
                }
            }.build()
        setContent {
            ApplicationTheme {
                Scaffold {
                    Application(
                        imageLoader = imgLoader,
                        musicPlayer = musicPlayer,
                        onNextTrackClick = { playNextTrack() },
                        onPreviousTrackClick = { playPreviousTrack() },
                        onPlayPause = { id -> playTrack(id) },
                    )
                }
            }
        }
    }
}