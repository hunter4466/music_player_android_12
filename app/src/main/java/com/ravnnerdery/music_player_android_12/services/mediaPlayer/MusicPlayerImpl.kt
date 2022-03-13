package com.ravnnerdery.music_player_android_12.services.mediaPlayer

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import com.ravnnerdery.domain.models.Track
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MusicPlayerImpl @Inject constructor(
    @ApplicationContext private var context: Context
) : MusicPlayer {
    private var musicList: List<Pair<Track, MediaPlayer>> = emptyList()
    private var currentTrack: Pair<Track, MediaPlayer>? = null
    private var firstPlay = true

    override fun feedMusicList(list: List<Pair<Track, MediaPlayer>>) {
        Log.wtf("APPTRACE", "HOW MANY TIMES IS THIS BEING CALLED?")
        musicList = list
        currentTrack = musicList.first()
    }

    override fun getCurrentSongId(): String {
        return currentTrack?.first?.id ?: "none"
    }

    override fun getCurrentSongData(): Flow<Track> = flow {
        while (true) {
            emit(currentTrack?.first ?: Track("0", "", "", 1))
            delay(500)
        }
    }.flowOn(Dispatchers.IO)

    override fun playPauseSong(id: String) {
        try {
            musicList.find { it.first.id == id }?.let { track ->
                if (track.second.isPlaying) {
                    track.second.pause()
                } else {
                    if (!firstPlay) {
                        if (track != currentTrack) {
                            currentTrack?.second?.seekTo(0)
                        }
                        currentTrack?.second?.pause()
                    }
                    currentTrack = track
                    currentTrack?.second?.start()
                    firstPlay = false
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Track was not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun stopCurrent() {
        currentTrack?.second?.seekTo(0)
        currentTrack?.second?.pause()
    }

    override fun getMusicList(): Flow<List<Track>> = flow {
        while (true) {
            emit(musicList.map { it.first })
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)

    override fun getCurrentSongPositionFLow(): Flow<Int> = flow {
        while (true) {
            val position: Int = currentTrack?.second?.currentPosition ?: 0
            emit(position)
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)

    override fun getCurrentSongDuration(): Flow<Int> = flow {
        emit(currentTrack?.second?.duration ?: 0)
    }.flowOn(Dispatchers.IO)

    override fun playCurrentOn(position: Int) {
        currentTrack?.second?.seekTo(position)
    }

    override fun playNext() {
        val nextTrack: Pair<Track, MediaPlayer> =
            if (musicList.indexOf(currentTrack) == musicList.size - 1) {
                musicList.first()
            } else {
                musicList[musicList.indexOf(currentTrack) + 1]
            }
        playPauseSong(nextTrack.first.id)
    }

    override fun playPrevious() {
        val previousTrack: Pair<Track, MediaPlayer> = if (musicList.indexOf(currentTrack) == 0) {
            musicList.last()
        } else {
            musicList[musicList.indexOf(currentTrack) - 1]
        }
        playPauseSong(previousTrack.first.id)
    }
}