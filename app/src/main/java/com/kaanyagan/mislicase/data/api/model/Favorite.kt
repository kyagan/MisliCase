package com.kaanyagan.mislicase.data.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val matchId: Int
)