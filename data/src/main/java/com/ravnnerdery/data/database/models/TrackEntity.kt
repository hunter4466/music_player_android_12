package com.ravnnerdery.data.database.models

import com.ravnnerdery.data.mappers.DomainMapper
import com.ravnnerdery.domain.models.Track

data class TrackEntity(
    var id: Any = "",
    var artist: String = "",
    var name: String = "",
    var trackUrl: String = "",
    var trackThumbnail: String = ""
) : DomainMapper<Track> {
    override fun mapToDomainModel(): Track = Track(id, artist, name, trackUrl, trackThumbnail)
}