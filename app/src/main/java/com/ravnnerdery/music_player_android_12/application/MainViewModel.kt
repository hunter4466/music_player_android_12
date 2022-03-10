package com.ravnnerdery.music_player_android_12.application

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ravnnerdery.data.useCases.ProvideTracksFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    provideTracksFlowUseCase: ProvideTracksFlowUseCase
) : AndroidViewModel(application) {
    val tracksFlow = provideTracksFlowUseCase.execute()
}