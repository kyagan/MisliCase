package com.kaanyagan.mislicase.data.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true) val matchId: Int = 0,
    val i: Int
)