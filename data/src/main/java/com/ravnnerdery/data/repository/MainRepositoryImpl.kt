package com.ravnnerdery.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.ravnnerdery.data.database.FirestoreDatabase
import com.ravnnerdery.data.database.models.TrackEntity
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.domain.other.Constants
import com.ravnnerdery.domain.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val firestoreDatabase: FirestoreDatabase
): MainRepository {

    override fun provideTracksFlow(): Flow<List<Track>> = flow {
        while (true) {
            try {
                emit(firestoreDatabase.getAllTracks().map{ it.mapToDomainModel() })
            } catch (e: Exception) {
                Log.wtf("Exception:","Database: $e")
                emit(emptyList())
            }
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)
}