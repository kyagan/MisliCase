package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.ListItem
import com.kaanyagan.mislicase.data.api.model.ListItemType
import com.kaanyagan.mislicase.data.api.service.LeagueAndMatchService
import javax.inject.Inject

class LeagueAndMatchRepositoryImpl @Inject constructor(
    private val leagueAndMatchService: LeagueAndMatchService,
    private val favoriteRepository: FavoriteRepository
):LeagueAndMatchRepository {
    override suspend fun getAllLeaguesAndMatches(): List<ListItem> {
        val allMatches = leagueAndMatchService.getAllMatches().data
        val filteredMatches = allMatches.filter { it.sc.st == 5 }
        val sortedMatches = filteredMatches.sortedWith(compareBy({ it.to.sn }, { it.d }))

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