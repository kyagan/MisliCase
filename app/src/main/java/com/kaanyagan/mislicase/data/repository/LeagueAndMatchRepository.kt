package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.ListItem

interface LeagueAndMatchRepository {

    suspend fun getAllLeaguesAndMatches():List<ListItem>
}