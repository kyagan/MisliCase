package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.data.api.model.ListItem
import com.kaanyagan.mislicase.data.api.model.ListItemType
import com.kaanyagan.mislicase.data.api.service.MatchService
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val matchService: MatchService,
    private val favoriteRepository: FavoriteRepository
):MatchRepository {
    override suspend fun getAllMatches(): List<ListItem> {
        val allMatches = matchService.getAllMatches().data
        val filteredMatches = allMatches.filter { it.sc.st == 5 }
        val sortedMatches = filteredMatches.sortedWith(compareBy({ it.to.sn }, { it.d }))

        val list = sortedMatches.groupBy { it.to.i }.flatMap { (leagueId, matches) ->
            val headerItem = ListItem(data = matches.first(), listItemType = ListItemType.HEADER)
            val matchItems = matches.map {
                ListItem(data = it, listItemType = ListItemType.MATCH, favoriteRepository.isMatchFavorite(it.i))
            }
            listOf(headerItem) + matchItems
        }

        return list
    }
}