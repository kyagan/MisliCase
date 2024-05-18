package com.kaanyagan.mislicase.data.state

sealed class FavoriteControlState{
    object Idle:FavoriteControlState()
    object Loading:FavoriteControlState()
    class FavoriteStatus(val isFavorite: Boolean):FavoriteControlState()
    class Error(val throwable: Throwable):FavoriteControlState()
}