package com.kaanyagan.mislicase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kaanyagan.mislicase.data.api.model.Favorite

// Data access object (DAO) for favorite operations
@Dao
interface FavoriteDao {

    // Adds a new favorite into the database
    @Insert
    suspend fun addFavorite(favorite: Favorite)

    // Checks whether a particular match is a favorite in the database
    @Query("select exists(select 1 from favorite where matchId =:matchId)")
    suspend fun checkFavorite(matchId: Int): Boolean

    // Removes a specific match from favorites in the database
    @Query("delete from favorite where matchId = :matchId")
    suspend fun removeFavorite(matchId:Int)

}