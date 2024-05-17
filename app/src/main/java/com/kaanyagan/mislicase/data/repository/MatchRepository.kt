package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.Data

interface MatchRepository {

    suspend fun getAllMatches():List<Data>
}