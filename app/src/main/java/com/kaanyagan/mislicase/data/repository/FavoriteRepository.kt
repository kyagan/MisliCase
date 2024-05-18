package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.Favorite

interface FavoriteRepository {
    suspend fun addFavorite(favorite: Favorite)
    suspend fun deleteFavorite(i: Int)
    suspend fun checkFavorite(i:Int): Int
}