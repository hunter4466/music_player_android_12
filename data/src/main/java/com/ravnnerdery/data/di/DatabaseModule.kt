package com.ravnnerdery.data.di

import com.ravnnerdery.data.database.FirestoreDatabase
import com.ravnnerdery.data.database.FirestoreDatabaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideFirestoreDatabase(
        firestoreDatabaseImpl: FirestoreDatabaseImpl
    ): FirestoreDatabase {
        return firestoreDatabaseImpl
    }
}