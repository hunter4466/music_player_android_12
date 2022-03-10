package com.ravnnerdery.music_player_android_12.application

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.music_player_android_12.services.MusicService
import com.ravnnerdery.music_player_android_12.ui.Application
import com.ravnnerdery.starwarschallenge.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
) : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    fun makeServiceStart() {
        Intent(this, MusicService::class.java).also {
            startService(it)
        }
    }

    fun stopService() {
        Intent(this, MusicService::class.java).also {
            stopService(it)
        }
    }

    fun changeService() {
        Intent(this, MusicService::class.java).also {
            it.putExtra("Extra_data", "Some Data")
            startService(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
                    Application(viewModel = mainViewModel, imageLoader = imgLoader,
                    stopSomeService = { stopService() }, startSomeService = { makeServiceStart() }, changeSomeService = { changeService() })
                }
            }
        }
    }
}