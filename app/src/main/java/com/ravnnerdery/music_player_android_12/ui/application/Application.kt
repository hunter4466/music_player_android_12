package com.ravnnerdery.music_player_android_12.ui.application


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.ravnnerdery.music_player_android_12.application.MainViewModel
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
import com.ravnnerdery.music_player_android_12.ui.application.navhost.NavigationHost
import com.ravnnerdery.music_player_android_12.ui.application.topbar.TopBar

@Composable
fun Application(
    musicPlayer: MusicPlayer,
    imageLoader: ImageLoader,
    onNextTrackClick: () -> Unit,
    onPreviousTrackClick: () -> Unit,
    onPlayPause: (String) -> Unit
) {
    val musicList by musicPlayer.getMusicList().collectAsState(initial = emptyList())
    val activeTrackData by musicPlayer.getCurrentSongData().collectAsState(initial = null)
    val navController = rememberNavController()

    Column {
        TopBar()
        NavigationHost(
            currentTrackData = activeTrackData,
            navController = navController,
            imageLoader = imageLoader,
            onNextTrackClick = { onNextTrackClick() },
            onPreviousTrackClick = { onPreviousTrackClick() },
            onPlayPause = { onPlayPause(it) },
        )
    }
}