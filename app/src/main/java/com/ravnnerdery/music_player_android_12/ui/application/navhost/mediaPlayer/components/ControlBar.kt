package com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.ravnnerdery.music_player_android_12.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ravnnerdery.domain.models.Track
import kotlin.math.roundToInt

@Composable
fun ControlBar(
    onNextTrackClick: () -> Unit,
    onPreviousTrackClick: () -> Unit,
    onPlayPause: (String) -> Unit,
    currentTrackData: Track?,
    sliderPosition: Int,
    sliderLength: Int,
    seekTo: (Int) -> Unit,
) {
    val position by rememberSaveable { mutableStateOf(0) }
    var isUserDragging by rememberSaveable { mutableStateOf(false) }
    Surface(color = MaterialTheme.colors.onPrimary) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onPreviousTrackClick() },
                    modifier = Modifier.padding(start = 60.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.play_previous_btn_asset),
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp)
                    )
                }
                IconButton(
                    onClick = { onPlayPause(currentTrackData?.id ?: "backinblack") }) {
                    Icon(
                        painterResource(R.drawable.play_btn_asset),
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp)
                    )
                }
                IconButton(
                    onClick = { onNextTrackClick() },
                    modifier = Modifier.padding(end = 60.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.play_next_btn_asset),
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp)
                    )
                }
            }


            Slider(
                value = if (!isUserDragging) {
                    sliderPosition.toFloat()
                } else {
                    position.toFloat()
                },
                onValueChange = {
                    isUserDragging = true
                    seekTo(it.roundToInt())
                },
                valueRange = 0f..sliderLength.toFloat(),
                steps = 0,
                onValueChangeFinished = {
                    isUserDragging = false
                },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colors.secondary,
                    activeTrackColor = MaterialTheme.colors.secondary
                ),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
        }
    }
}