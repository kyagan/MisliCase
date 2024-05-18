package com.kaanyagan.mislicase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kaanyagan.mislicase.data.api.model.Favorite

@Dao
interface FavoriteDao {

    @Insert
    suspend fun addFavorite(favorite: Favorite)

    @Query("select count(*) from favorite where i =:i")
    suspend fun checkFavorite(i: Int): Int

    @Query("delete from favorite where i = :i")
    suspend fun deleteFavorite(i:Int)

}