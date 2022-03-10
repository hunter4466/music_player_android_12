package com.ravnnerdery.data.database.models

import com.ravnnerdery.data.mappers.DomainMapper
import com.ravnnerdery.domain.models.Track

class TrackEntity(
    val id: String,
    val artist: String,
    val name: String,
    val trackUrl: String,
    val trackThumbnail: String,
): DomainMapper<Track> {
    override fun mapToDomainModel(): Track = Track(id, artist, name, trackUrl, trackThumbnail)
}