package com.kaanyagan.mislicase.data.state

import com.kaanyagan.mislicase.data.api.model.ListItem

sealed class LeagueAndMatchListState{
    object Idle : LeagueAndMatchListState()
    object Loading : LeagueAndMatchListState()
    object Empty : LeagueAndMatchListState()
    class Result(val matches: MutableList<ListItem>) : LeagueAndMatchListState()
    class Error(val throwable: Throwable? = null) : LeagueAndMatchListState()
}
