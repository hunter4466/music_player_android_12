package com.ravnnerdery.music_player_android_12.ui.application.navhost

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.music_player_android_12.ui.application.navhost.alarm.AlarmScheduler
import com.ravnnerdery.music_player_android_12.ui.application.navhost.mediaPlayer.MediaPlayer

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavigationHost(
    currentTrackData: Track?,
    navController: NavHostController,
    onNextTrackClick: () -> Unit,
    onPreviousTrackClick: () -> Unit,
    onPlayPause: (String) -> Unit,
    sliderPosition: Int,
    sliderLength: Int,
    seekTo: (Int) -> Unit,
    musicList: List<Track>,
    bottomSheetInfo: Pair<String, String>,
    setAlarm: (Int, String) -> Unit,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
) {

    NavHost(navController = navController, startDestination = "media_player") {
        composable("media_player") {
            MediaPlayer(
                currentTrackData = currentTrackData,
                onNextTrackClick = { onNextTrackClick() },
                onPreviousTrackClick = { onPreviousTrackClick() },
                onPlayPause = { onPlayPause(it) },
                sliderPosition = sliderPosition,
                sliderLength = sliderLength,
                seekTo = { seekTo(it) },
                musicList = musicList,
                bottomSheetInfo = bottomSheetInfo,
                bottomSheetScaffoldState = bottomSheetScaffoldState,
            )
        }
        composable("alarm_scheduler") {
            AlarmScheduler(
                setAlarm = { time, id -> setAlarm(time, id) },
                ringList = musicList,
            )
        }
    }
}