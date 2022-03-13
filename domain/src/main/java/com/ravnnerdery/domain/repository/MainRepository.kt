package com.ravnnerdery.domain.repository

import android.media.MediaPlayer
import com.ravnnerdery.domain.models.Track
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun provideLoadingState(): Flow<Boolean>
    fun changeLoadingState(state: Boolean)
    fun provideTracksFlow(): Flow<List<Pair<Track, MediaPlayer>>>
}