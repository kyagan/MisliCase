package com.kaanyagan.mislicase.data.state

sealed class FavoriteAddOrRemoveState{
    object Idle:FavoriteAddOrRemoveState()
    object Loading:FavoriteAddOrRemoveState()
    object Add:FavoriteAddOrRemoveState()
    object Remove:FavoriteAddOrRemoveState()
    class Error(val throwable: Throwable):FavoriteAddOrRemoveState()
}