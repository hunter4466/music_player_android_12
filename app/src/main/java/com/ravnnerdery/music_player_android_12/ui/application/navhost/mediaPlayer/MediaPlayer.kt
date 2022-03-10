package com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.music_player_android_12.R
import com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer.components.ControlBar

@Composable
fun MediaPlayer(
    imageLoader: ImageLoader,
    currentTrackData: Track?,
    onNextTrackClick: () -> Unit,
    onPreviousTrackClick: () -> Unit,
    onPlayPause: (String) -> Unit
) {
    Column {
        Image(
            painterResource(
                currentTrackData?.trackThumbnail?.toInt() ?: R.drawable.ic_launcher_background
            ),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        ControlBar(
            onNextTrackClick = { onNextTrackClick() },
            onPreviousTrackClick = { onPreviousTrackClick() },
            onPlayPause = { onPlayPause(it) },
            currentTrackData = currentTrackData,
        )
    }
}