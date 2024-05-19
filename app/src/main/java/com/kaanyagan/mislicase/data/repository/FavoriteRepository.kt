package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.Favorite

interface FavoriteRepository {
    suspend fun addFavorite(favorite: Favorite)
    suspend fun removeFavorite(i: Int)
    suspend fun isMatchFavorite(dataId:Int): Boolean
}