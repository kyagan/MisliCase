package com.kaanyagan.mislicase.data.di

import com.kaanyagan.mislicase.data.repository.LeagueAndMatchRepository
import com.kaanyagan.mislicase.data.repository.LeagueAndMatchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMatchRepository(matchRepositoryImpl: LeagueAndMatchRepositoryImpl): LeagueAndMatchRepository = matchRepositoryImpl
}