package com.ravnnerdery.music_player_android_12.di

import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayer
import com.ravnnerdery.music_player_android_12.services.mediaPlayer.MusicPlayerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideMusicPlayer(
        musicPlayerImpl: MusicPlayerImpl
    ): MusicPlayer {
        return musicPlayerImpl
    }

}