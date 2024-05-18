package com.kaanyagan.mislicase.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaanyagan.mislicase.data.api.model.Favorite
import com.kaanyagan.mislicase.data.dao.FavoriteDao

@Database(entities = [Favorite::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
}