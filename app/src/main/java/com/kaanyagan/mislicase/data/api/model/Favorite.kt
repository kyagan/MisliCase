package com.kaanyagan.mislicase.data.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//Model used to add favorite property to database
@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val matchId: Int
)