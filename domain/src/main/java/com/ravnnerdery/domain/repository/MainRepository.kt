package com.ravnnerdery.domain.repository

import com.ravnnerdery.domain.models.Track
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun provideTracksFlow(): Flow<List<Track>>
}