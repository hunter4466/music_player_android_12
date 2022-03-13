package com.ravnnerdery.data.di

import com.ravnnerdery.data.useCases.*
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

    @Provides
    @Singleton
    fun provideLoadingStateUseCase(
        provideLoadingStateUseCaseImpl: ProvideLoadingStateUseCaseImpl
    ): ProvideLoadingStateUseCase {
        return provideLoadingStateUseCaseImpl
    }

    @Provides
    @Singleton
    fun changeLoadingStateUseCase(
        changeLoadingStateUseCaseImpl: ChangeLoadingStateUseCaseImpl
    ): ChangeLoadingStateUseCase {
        return changeLoadingStateUseCaseImpl
    }
}