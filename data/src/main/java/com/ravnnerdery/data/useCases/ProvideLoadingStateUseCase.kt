package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProvideLoadingStateUseCase {
    fun execute(): Flow<Boolean>
}

class ProvideLoadingStateUseCaseImpl @Inject constructor(private val repo: MainRepository) :
    ProvideLoadingStateUseCase {
    override fun execute(): Flow<Boolean> {
        return repo.provideLoadingState()
    }
}