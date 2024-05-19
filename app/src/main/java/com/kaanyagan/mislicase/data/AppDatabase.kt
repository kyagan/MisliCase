package com.kaanyagan.mislicase.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaanyagan.mislicase.data.api.model.Favorite
import com.kaanyagan.mislicase.data.dao.FavoriteDao

// Database class for using Room
// Returns the DAO interface for accessing favorite data
@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
}