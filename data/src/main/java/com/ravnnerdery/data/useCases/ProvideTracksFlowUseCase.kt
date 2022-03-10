package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.models.Track
import com.ravnnerdery.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProvideTracksFlowUseCase {
    fun execute(): Flow<List<Track>>
}

class ProvideTracksFlowUseCaseImpl @Inject constructor(private val repo: MainRepository): ProvideTracksFlowUseCase{
    override fun execute(): Flow<List<Track>> = repo.provideTracksFlow()
}