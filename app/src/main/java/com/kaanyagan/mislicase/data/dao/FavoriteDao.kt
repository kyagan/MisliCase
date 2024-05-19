package com.kaanyagan.mislicase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kaanyagan.mislicase.data.api.model.Favorite

@Dao
interface FavoriteDao {

    @Insert
    suspend fun addFavorite(favorite: Favorite)

    @Query("select exists(select 1 from favorite where matchId =:matchId)")
    suspend fun checkFavorite(matchId: Int): Boolean

    @Query("delete from favorite where matchId = :matchId")
    suspend fun removeFavorite(matchId:Int)

}