package com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ravnnerdery.domain.models.Track

@Composable
fun ControlBar(
    onNextTrackClick: () -> Unit,
    onPreviousTrackClick: () -> Unit,
    onPlayPause: (String) -> Unit,
    currentTrackData: Track?,
) {

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(onClick = { onPreviousTrackClick() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            IconButton(onClick = { onPlayPause( currentTrackData?.id ?: "0" ) }) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            IconButton(onClick = { onNextTrackClick() }) {
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}