package com.ravnnerdery.music_player_android_12.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import coil.ImageLoader
import com.ravnnerdery.music_player_android_12.application.MainViewModel

@Composable
fun Application(
    viewModel: MainViewModel,
    imageLoader: ImageLoader,
    startSomeService: () -> Unit,
    stopSomeService: () -> Unit,
    changeSomeService: () -> Unit) {

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