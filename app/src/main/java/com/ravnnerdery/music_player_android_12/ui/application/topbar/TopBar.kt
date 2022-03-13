package com.ravnnerdery.music_player_android_12.ui.application.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    topBarTitle: String,
    onNavigationBackClicked: () -> Unit,
    onNavigationToAlarmClicked: () -> Unit,
    hasNavigationBackIcon: Boolean,
) {
    TopAppBar(
        title = {
            Text(
                text = topBarTitle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 80.dp),
                color = Color.White
            )
        },
        navigationIcon = {
            if (hasNavigationBackIcon) {
                IconButton(
                    onClick = { onNavigationBackClicked() }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White)
                }
            } else {
                IconButton(
                    onClick = { onNavigationToAlarmClicked() }
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = "", tint = Color.White)
                }
            }
        },
        backgroundColor = Color.Black,
    )
}