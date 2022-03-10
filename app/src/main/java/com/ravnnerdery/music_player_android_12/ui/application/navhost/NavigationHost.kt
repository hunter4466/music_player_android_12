package com.ravnnerdery.music_player_android_12.ui.application.navhost

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.ImageLoader
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer.MediaPlayer

@Composable
fun NavigationHost(
    currentTrackData: Track?,
    navController: NavHostController,
    imageLoader: ImageLoader,
    onNextTrackClick: () -> Unit,
    onPreviousTrackClick: () -> Unit,
    onPlayPause: (String) -> Unit
) {
    NavHost( navController = navController, startDestination = "media_player") {
        composable("media_player"){
            MediaPlayer(
                imageLoader = imageLoader,
                currentTrackData = currentTrackData,
                onNextTrackClick = { onNextTrackClick() },
                onPreviousTrackClick = { onPreviousTrackClick() },
                onPlayPause = { onPlayPause(it) },
            )
        }
        composable("alarm_scheduler") {

        }
    }

}