package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import javax.inject.Inject

interface ChangeLoadingStateUseCase {
    fun execute(state: Boolean)
}

class ChangeLoadingStateUseCaseImpl @Inject constructor(private val repo: MainRepository) :
    ChangeLoadingStateUseCase {
    override fun execute(state: Boolean) {
        repo.changeLoadingState(state)
    }
}