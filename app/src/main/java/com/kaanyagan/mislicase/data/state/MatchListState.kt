package com.kaanyagan.mislicase.data.state

import com.kaanyagan.mislicase.data.api.model.Data

sealed class MatchListState{
    object Idle : MatchListState()
    object Loading : MatchListState()
    object Empty : MatchListState()
    class Result(val matches: List<Data>) : MatchListState()
    class Error(val throwable: Throwable? = null) : MatchListState()
}
