package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.Favorite
import com.kaanyagan.mislicase.data.dao.FavoriteDao
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
): FavoriteRepository {


    override suspend fun addFavorite(favorite: Favorite) {
        return favoriteDao.addFavorite(favorite)
    }

    override suspend fun deleteFavorite(matchId: Int) {
        return favoriteDao.deleteFavorite(matchId)
    }

    override suspend fun isMatchFavorite(dataId: Int): Boolean {
        return favoriteDao.checkFavorite(dataId)
    }

}