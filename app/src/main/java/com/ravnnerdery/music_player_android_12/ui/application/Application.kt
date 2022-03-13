package com.ravnnerdery.music_player_android_12.ui.application

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.ravnnerdery.music_player_android_12.application.MainViewModel
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
import com.ravnnerdery.music_player_android_12.ui.application.navhost.NavigationHost
import com.ravnnerdery.music_player_android_12.ui.application.other.Loading
import com.ravnnerdery.music_player_android_12.ui.application.topbar.TopBar

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Application(
    viewModel: MainViewModel,
    musicPlayer: MusicPlayer,
    imageLoader: ImageLoader,
    onNextTrackClick: () -> Unit,
    onPreviousTrackClick: () -> Unit,
    onPlayPause: (String) -> Unit,
    setAlarm: (Int, String) -> Unit,
) {
    val musicList by musicPlayer.getMusicList().collectAsState(initial = emptyList())
    val activeTrackData by musicPlayer.getCurrentSongData().collectAsState(initial = null)
    val currentPlayingIsAt by musicPlayer.getCurrentSongPositionFLow().collectAsState(initial = 0)
    val currentPlayingLength by musicPlayer.getCurrentSongDuration().collectAsState(initial = 0)
    val loadingState by viewModel.loadingState().collectAsState(initial = true)
    var bottomSheetInfo by rememberSaveable { mutableStateOf(Pair("", "")) }
    var topBarTitle by rememberSaveable { mutableStateOf("Music Player") }
    var hasNavigationBackIcon by rememberSaveable { mutableStateOf(false) }
    val navController = rememberNavController()
    fun updateBottomSheetInfo() {
        bottomSheetInfo = Pair(activeTrackData?.album ?: "", activeTrackData?.name ?: "")
    }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    if (loadingState) {
        Loading(imageLoader = imageLoader)
    } else {

        Column {
            TopBar(
                topBarTitle = topBarTitle,
                onNavigationBackClicked = {
                    topBarTitle = "Music Player"
                    navController.navigateUp()
                    hasNavigationBackIcon = false
                },
                hasNavigationBackIcon = hasNavigationBackIcon,
                onNavigationToAlarmClicked = {
                    topBarTitle = "Alarm Scheduler"
                    navController.navigate("alarm_scheduler")
                    hasNavigationBackIcon = true
                }
            )
            NavigationHost(
                currentTrackData = activeTrackData,
                navController = navController,
                onNextTrackClick = {
                    onNextTrackClick()
                    updateBottomSheetInfo()
                },
                onPreviousTrackClick = {
                    onPreviousTrackClick()
                    updateBottomSheetInfo()
                },
                onPlayPause = {
                    onPlayPause(it)
                    updateBottomSheetInfo()
                    updateBottomSheetInfo()
                },
                sliderPosition = currentPlayingIsAt,
                sliderLength = currentPlayingLength,
                seekTo = { musicPlayer.playCurrentOn(it) },
                musicList = musicList,
                bottomSheetInfo = bottomSheetInfo,
                setAlarm = { time, id -> setAlarm(time, id) },
                bottomSheetScaffoldState = bottomSheetScaffoldState,
            )
        }
    }
}