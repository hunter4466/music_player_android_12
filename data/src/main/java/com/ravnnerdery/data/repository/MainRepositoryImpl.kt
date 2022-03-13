package com.ravnnerdery.data.repository

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import com.ravnnerdery.data.R
import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.domain.repository.MainRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : MainRepository {
    private var loadingState = MutableStateFlow(true)

    override fun provideLoadingState(): Flow<Boolean> = loadingState

    override fun changeLoadingState(state: Boolean) {
        loadingState.value = state
    }

    override fun provideTracksFlow(): Flow<List<Pair<Track, MediaPlayer>>> = flow {
        val musicFields = R.raw::class.java.fields
        val list = musicFields.mapIndexed { index, field ->
            val metaRetriever = MediaMetadataRetriever()
            val musicUriPath =
                "android.resource://com.ravnnerdery.music_player_android_12/raw/${field.name}"
            val musicUri = Uri.parse(musicUriPath)
            metaRetriever.setDataSource(context, musicUri)
            val track = context.resources.getIdentifier(field.name, "raw", context.packageName)
            Pair(
                Track(
                    id = field.name,
                    album = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
                        ?: "Unknown",
                    name = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
                        ?: "Unknown",
                    trackThumbnail = context.resources.getIdentifier(
                        field.name,
                        "drawable",
                        context.packageName
                    ),
                ), MediaPlayer.create(context, track)
            )
        }
        emit(list)
    }.flowOn(Dispatchers.IO)
}