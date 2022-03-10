package com.ravnnerdery.data.database

import com.ravnnerdery.data.database.models.TrackEntity

interface FirestoreDatabase {
    suspend fun getAllTracks(): List<TrackEntity>
}