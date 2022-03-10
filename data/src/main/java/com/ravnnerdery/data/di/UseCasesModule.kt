package com.ravnnerdery.data.di

import com.ravnnerdery.data.useCases.ProvideTracksFlowUseCase
import com.ravnnerdery.data.useCases.ProvideTracksFlowUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCasesModule {
    @Provides
    @Singleton
    fun provideTracksFlowUseCase(
        provideTracksFlowUseCaseImpl: ProvideTracksFlowUseCaseImpl
    ): ProvideTracksFlowUseCase {
        return provideTracksFlowUseCaseImpl
    }
}