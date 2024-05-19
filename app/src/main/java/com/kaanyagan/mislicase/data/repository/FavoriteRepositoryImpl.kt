package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.Favorite
import com.kaanyagan.mislicase.data.dao.FavoriteDao
import javax.inject.Inject

// Implementation of the FavoriteRepository interface for managing favorite data
class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
): FavoriteRepository {

    // Adds a match to favorites
    override suspend fun addFavorite(favorite: Favorite) {
        return favoriteDao.addFavorite(favorite)
    }

    // Removes a match from favorites
    override suspend fun removeFavorite(matchId: Int) {
        return favoriteDao.removeFavorite(matchId)
    }

    // Checks if a match is in favorites
    override suspend fun isMatchFavorite(dataId: Int): Boolean {
        return favoriteDao.checkFavorite(dataId)
    }

}