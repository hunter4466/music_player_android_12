package com.ravnnerdery.music_player_android_12.application

import androidx.lifecycle.ViewModel
import com.ravnnerdery.data.useCases.ProvideLoadingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var provideLoadingStateUseCase: ProvideLoadingStateUseCase
) : ViewModel() {
    fun loadingState() = provideLoadingStateUseCase.execute()
}