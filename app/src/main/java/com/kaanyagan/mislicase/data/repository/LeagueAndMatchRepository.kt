package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.ListItem

// Interface defining the method for retrieving all leagues and matches
interface LeagueAndMatchRepository {

    // Retrieves all leagues and matches
    suspend fun getAllLeaguesAndMatches():List<ListItem>
}