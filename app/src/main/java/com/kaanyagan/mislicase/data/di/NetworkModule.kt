package com.kaanyagan.mislicase.data.di

import com.kaanyagan.mislicase.Constants
import com.kaanyagan.mislicase.data.api.service.LeagueAndMatchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Module for providing network-related dependencies using Dagger Hilt
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Provides the Retrofit instance
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Provides the LeagueAndMatchService instance
    @Provides
    fun provideLeagueAndMatchService(retrofit: Retrofit): LeagueAndMatchService = retrofit.create(LeagueAndMatchService::class.java)

}