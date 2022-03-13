package com.ravnnerdery.music_player_android_12.ui.application.navhost.alarm.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ravnnerdery.domain.models.Track

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RingList(
    onSelect: (String) -> Unit,
    ringList: List<Track>
) {
    Card() {
        LazyColumn(
            modifier = Modifier
                .height(250.dp)
                .width(220.dp)
        ) {
            itemsIndexed(ringList) { _, item ->
                Card(
                    onClick = { onSelect(item.id) }
                ) {
                    Text(text = item.name, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}