package com.kaanyagan.mislicase.data.state

import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.data.api.model.ListItem

sealed class MatchListState{
    object Idle : MatchListState()
    object Loading : MatchListState()
    object Empty : MatchListState()
    class Result(val matches: List<ListItem>) : MatchListState()
    class Error(val throwable: Throwable? = null) : MatchListState()
}
