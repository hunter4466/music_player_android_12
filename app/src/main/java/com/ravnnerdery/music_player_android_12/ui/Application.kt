package com.ravnnerdery.music_player_android_12.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil.ImageLoader
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.music_player_android_12.application.MainViewModel

@Composable
fun Application(
    viewModel: MainViewModel,
    imageLoader: ImageLoader,
    startSomeService: () -> Unit,
    stopSomeService: () -> Unit,
    changeSomeService: () -> Unit) {

    val tracksList by viewModel.tracksFlow.collectAsState(initial = emptyList())
    tracksList.forEach {
        Log.wtf("MARIOCH","some data: ${it.artist}")
    }

    Column{
        Button(onClick = { startSomeService() }) {
            Text("START")
        }
        Button(onClick = { stopSomeService() }) {
            Text("STOP")
        }
        Button(onClick = { changeSomeService() }) {
            Text("CHANGE")
        }
    }

}