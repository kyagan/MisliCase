package com.kaanyagan.mislicase.data.state

// This class representing the different states of a favorite message
sealed class FavoriteMessageState{
    // Represents the idle state where no action is being performed
    object Idle:FavoriteMessageState()
    // Represents the state where a favorite is added successfully
    object Added:FavoriteMessageState()
    // Represents the state where a favorite is removed successfully
    object Removed:FavoriteMessageState()
    // Represents an error state with the associated throwable
    class Error(val throwable: Throwable):FavoriteMessageState()
}