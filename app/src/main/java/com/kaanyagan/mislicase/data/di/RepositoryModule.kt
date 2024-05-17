package com.kaanyagan.mislicase.data.di

import com.kaanyagan.mislicase.data.repository.MatchRepository
import com.kaanyagan.mislicase.data.repository.MatchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMatchRepository(matchRepositoryImpl: MatchRepositoryImpl): MatchRepository = matchRepositoryImpl
}