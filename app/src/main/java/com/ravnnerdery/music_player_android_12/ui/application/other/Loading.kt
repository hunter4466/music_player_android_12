package com.ravnnerdery.music_player_android_12.ui.application.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.ravnnerdery.music_player_android_12.R

@Composable
fun Loading(imageLoader: ImageLoader) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Loading",
            color = Color.Gray,
            style = MaterialTheme.typography.h5
        )
        Image(
            painter = rememberImagePainter(
                data = R.drawable.loadingbuffering,
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}