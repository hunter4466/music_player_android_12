package com.ravnnerdery.data.database

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.ravnnerdery.data.database.models.TrackEntity
import com.ravnnerdery.domain.other.Constants.DATABASE_COLLECTION
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class FirestoreDatabaseImpl @Inject constructor(): FirestoreDatabase {
    private val firestore = FirebaseFirestore.getInstance()
    private val trackCompile = firestore.collection(DATABASE_COLLECTION)

    override suspend fun getAllTracks(): List<TrackEntity> {
        return try {
            trackCompile.get().await().toObjects(TrackEntity::class.java)
        } catch (e: Exception) {
            Log.wtf("Exception:", "Database: $e")
            emptyList()
        }
    }
}