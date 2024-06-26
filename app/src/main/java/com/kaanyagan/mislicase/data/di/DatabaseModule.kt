package com.kaanyagan.mislicase.data.di

import android.content.Context
import androidx.room.Room
import com.kaanyagan.mislicase.data.AppDatabase
import com.kaanyagan.mislicase.data.dao.FavoriteDao
import com.kaanyagan.mislicase.data.repository.FavoriteRepository
import com.kaanyagan.mislicase.data.repository.FavoriteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Module for providing database-related dependencies using Dagger Hilt
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    //Provides the AppDatabase instance
    @Provides
    @Singleton
    fun provideDatabaseModule(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    // Provides the FavoriteDao instance
    @Provides
    @Singleton
    fun provideFavorite(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }

    // Provides the FavoriteRepository instance
    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository = favoriteRepositoryImpl

}