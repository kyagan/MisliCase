package com.kaanyagan.mislicase

import android.content.Context
import androidx.room.Room
import com.kaanyagan.mislicase.data.AppDatabase

object RoomDatabase{

    fun getDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java,"database").build()
}