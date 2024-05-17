package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.data.api.model.ListItem

interface MatchRepository {

    suspend fun getAllMatches():List<ListItem>
}