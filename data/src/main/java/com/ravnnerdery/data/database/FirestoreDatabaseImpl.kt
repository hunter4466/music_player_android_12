package com.ravnnerdery.data.database

import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.ravnnerdery.data.database.models.TrackEntity
import com.ravnnerdery.domain.other.Constants.DATABASE_COLLECTION
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class FirestoreDatabaseImpl @Inject constructor(
    @ApplicationContext context: Context
): FirestoreDatabase {
    init {
        FirebaseApp.initializeApp(context)
    }
    val firestore = FirebaseFirestore.getInstance()
    val trackCompile = firestore.collection(DATABASE_COLLECTION)

    override suspend fun getAllTracks(): List<TrackEntity> {
        return try {
            trackCompile.get().await().toObjects(TrackEntity::class.java)
        } catch (e: Exception) {
            Log.wtf("MARIOCH", "Database: $e")
            emptyList()
        }
    }
}
