package com.kaanyagan.mislicase.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.mislicase.data.repository.MatchRepository
import com.kaanyagan.mislicase.data.state.MatchListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val matchRepository: MatchRepository
): ViewModel(){

    private val _matchListState:MutableStateFlow<MatchListState> = MutableStateFlow(MatchListState.Idle)
    val matchListState:StateFlow<MatchListState> = _matchListState

    fun getAllMatches(){
        viewModelScope.launch {
            runCatching {
                _matchListState.value = MatchListState.Loading
                val matches = matchRepository.getAllMatches()
                if (matches.isEmpty()) _matchListState.value = MatchListState.Empty
                else _matchListState.value = MatchListState.Result(matches)
            }.onFailure {
                _matchListState.value = MatchListState.Error(it)
            }
        }
    }
}