package com.ravnnerdery.music_player_android_12.ui.application.navhost.alarm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.music_player_android_12.ui.application.navhost.alarm.components.RingList

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AlarmScheduler(
    setAlarm: (Int, String) -> Unit,
    ringList: List<Track>
) {
    var time by rememberSaveable { mutableStateOf("0") }
    var convertFactor = 1000
    var placeHolder by rememberSaveable { mutableStateOf("Seconds") }
    val parsedTime by rememberSaveable { mutableStateOf(time.toInt() * convertFactor) }
    var pickedId by rememberSaveable { mutableStateOf("") }
    var activeAlert by rememberSaveable { mutableStateOf(false) }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val (column) = createRefs()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Set scheduled Alarm in:", style = MaterialTheme.typography.h5)

            OutlinedTextField(
                singleLine = true,
                label = { Text(placeHolder) },
                value = time,
                modifier = Modifier.width(120.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                onValueChange = { time = it }
            )

            Row {
                Button(onClick = {
                    convertFactor = 1000
                    placeHolder = "Seconds"
                }, modifier = Modifier.padding(8.dp)) { Text(text = "Seconds") }

                Button(onClick = {
                    convertFactor = 60000
                    placeHolder = "Minutes"
                }, modifier = Modifier.padding(8.dp)) { Text(text = "Minutes") }

                Button(onClick = {
                    convertFactor = 360000
                    placeHolder = "Hours"
                }, modifier = Modifier.padding(8.dp)) { Text(text = "Hours") }
            }

            Text(text = "Choose a rington for your alarm", style = MaterialTheme.typography.h6)

            RingList(onSelect = { pickedId = it }, ringList = ringList)

            Button(onClick = {
                if (pickedId.isNotEmpty()) {
                    setAlarm(parsedTime, pickedId)
                } else {
                    activeAlert = true
                }
            }) { Text(text = "Set Alarm") }
            if (activeAlert) {
                Text(
                    text = "Please choose a ringtone for your Alarm",
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}
