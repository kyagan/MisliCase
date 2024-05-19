package com.kaanyagan.mislicase.data.di

import com.kaanyagan.mislicase.data.repository.LeagueAndMatchRepository
import com.kaanyagan.mislicase.data.repository.LeagueAndMatchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Module for providing repository-related dependencies using Dagger Hilt
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Provides the LeagueAndMatchRepository instance
    @Provides
    fun provideLeagueMatchRepository(matchRepositoryImpl: LeagueAndMatchRepositoryImpl): LeagueAndMatchRepository = matchRepositoryImpl
}