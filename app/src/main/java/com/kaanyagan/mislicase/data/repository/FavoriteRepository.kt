package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.Favorite

// Interface defining the methods for interacting with favorite data in the repository
interface FavoriteRepository {
    // Adds a match to favorites
    suspend fun addFavorite(favorite: Favorite)
    // Removes a match from favorites
    suspend fun removeFavorite(matchId: Int)
    // Checks if a match is in favorites
    suspend fun isMatchFavorite(dataId:Int): Boolean
}