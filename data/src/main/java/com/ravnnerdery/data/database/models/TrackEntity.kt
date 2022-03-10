package com.ravnnerdery.data.database.models

import com.ravnnerdery.data.mappers.DomainMapper
import com.ravnnerdery.domain.models.Track

data class TrackEntity(
    var id: String = "",
    var artist: String = "",
    var name: String = "",
    var trackUrl: Long = 0L,
    var trackThumbnail: Long = 0L
) : DomainMapper<Track> {
    override fun mapToDomainModel(): Track = Track(id, artist, name, trackUrl, trackThumbnail)
}