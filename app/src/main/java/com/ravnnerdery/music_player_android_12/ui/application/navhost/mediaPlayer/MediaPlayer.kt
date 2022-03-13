package com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.music_player_android_12.R
import com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer.components.ControlBar
import com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer.components.TracksList
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MediaPlayer(
    currentTrackData: Track?,
    onNextTrackClick: () -> Unit,
    onPreviousTrackClick: () -> Unit,
    onPlayPause: (String) -> Unit,
    sliderPosition: Int,
    sliderLength: Int,
    seekTo: (Int) -> Unit,
    musicList: List<Track>,
    bottomSheetInfo: Pair<String, String>,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
) {

    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Card(
                Modifier
                    .fillMaxWidth(),
                elevation = 16.dp,
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                backgroundColor = MaterialTheme.colors.primaryVariant
            ) {
                Column() {

                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                    bottomSheetScaffoldState.bottomSheetState.expand()
                                } else {
                                    bottomSheetScaffoldState.bottomSheetState.collapse()
                                }
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "More Info")
                    }
                    Text(
                        text = "Album: ${bottomSheetInfo.first}",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = "Artist and Name: ${bottomSheetInfo.second}",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }, sheetPeekHeight = 47.dp
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(modifier = Modifier.height(200.dp)) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    Image(
                        painterResource(
                            if (currentTrackData?.trackThumbnail ?: 2 > 1) {
                                currentTrackData?.trackThumbnail ?: 1
                            } else {
                                R.drawable.ic_launcher_background
                            }
                        ),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxHeight(),
                    )
                }

            }

            ControlBar(
                onNextTrackClick = { onNextTrackClick() },
                onPreviousTrackClick = { onPreviousTrackClick() },
                onPlayPause = { onPlayPause(it) },
                currentTrackData = currentTrackData,
                sliderPosition = sliderPosition,
                sliderLength = sliderLength,
                seekTo = { seekTo(it) },
            )
            TracksList(
                musicList = musicList,
                onPlayPause = { onPlayPause(it) }
            )
        }
    }
}