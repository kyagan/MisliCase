package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.ListItem
import com.kaanyagan.mislicase.data.api.model.ListItemType
import com.kaanyagan.mislicase.data.api.service.LeagueAndMatchService
import javax.inject.Inject

// Implementation of the LeagueAndMatchRepository interface for retrieving all leagues and matches
class LeagueAndMatchRepositoryImpl @Inject constructor(
    private val leagueAndMatchService: LeagueAndMatchService,
    private val favoriteRepository: FavoriteRepository
):LeagueAndMatchRepository {
    // Retrieves all leagues and matches
    override suspend fun getAllLeaguesAndMatches(): List<ListItem> {
        // Fetches all matches from the service
        val allMatches = leagueAndMatchService.getAllLeaguesAndMatches().data
        // Filters matches based on a specific condition (st=5 means finished matches)
        val filteredMatches = allMatches.filter { it.sc.st == 5 }
        // Sorts matches based on specific criteria (sn means league name, d means date)
        val sortedMatches = filteredMatches.sortedWith(compareBy({ it.to.sn }, { it.d }))

        // Groups matches by leagueId and creates a flat list
        val list = sortedMatches.groupBy { it.to.i }.flatMap { (leagueId, matches) ->
            val leagueItem = ListItem(data = matches.first(), listItemType = ListItemType.LEAGUE)
            val matchItems = matches.map {
                ListItem(data = it, listItemType = ListItemType.MATCH, favoriteRepository.isMatchFavorite(it.i))
            }
            listOf(leagueItem) + matchItems
        }

        return list
    }
}