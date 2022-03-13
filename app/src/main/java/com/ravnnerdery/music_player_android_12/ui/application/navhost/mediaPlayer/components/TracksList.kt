package com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ravnnerdery.domain.models.Track

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TracksList(
    musicList: List<Track>,
    onPlayPause: (String) -> Unit,
) {
    Surface(
        modifier = Modifier.padding(bottom = 48.dp)
    ) {
        LazyColumn {
            itemsIndexed(musicList) { _, item ->
                Card(
                    onClick = { onPlayPause(item.id) },
                ) {
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(start = 16.dp),
                        style = MaterialTheme.typography.h6
                    )
                }
                Divider(color = Color.DarkGray, thickness = 1.dp)
            }
        }
    }
}