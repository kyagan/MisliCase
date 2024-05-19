package com.kaanyagan.mislicase.data.state

sealed class FavoriteMessageState{
    object Idle:FavoriteMessageState()
    object Added:FavoriteMessageState()
    object Removed:FavoriteMessageState()
    class Error(val throwable: Throwable):FavoriteMessageState()
}