package com.kaanyagan.mislicase.data.state

import com.kaanyagan.mislicase.data.api.model.ListItem

// This class representing the different states of a league and match list
sealed class LeagueAndMatchListState{
    // Represents the idle state where no action is being performed
    object Idle : LeagueAndMatchListState()
    // Represents the loading state while fetching data
    object Loading : LeagueAndMatchListState()
    // Represents the empty state when there is no data available
    object Empty : LeagueAndMatchListState()
    // Represents the result state with the list of matches
    class Result(val matches: MutableList<ListItem>) : LeagueAndMatchListState()
    // Represents an error state with the associated throwable
    class Error(val throwable: Throwable? = null) : LeagueAndMatchListState()
}
