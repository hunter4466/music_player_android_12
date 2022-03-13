package com.ravnnerdery.music_player_android_12.services.mediaPlayer

import android.media.MediaPlayer
import com.ravnnerdery.domain.models.Track
import kotlinx.coroutines.flow.Flow

interface MusicPlayer {
    fun feedMusicList(list: List<Pair<Track, MediaPlayer>>)
    fun getMusicList(): Flow<List<Track>>
    fun playPauseSong(id: String)
    fun playNext()
    fun playPrevious()
    fun getCurrentSongPositionFLow(): Flow<Int>
    fun getCurrentSongDuration(): Flow<Int>
    fun getCurrentSongData(): Flow<Track>
    fun getCurrentSongId(): String
    fun playCurrentOn(position: Int)
    fun stopCurrent()
}