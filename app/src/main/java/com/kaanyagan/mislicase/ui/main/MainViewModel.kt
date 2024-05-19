package com.kaanyagan.mislicase.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.mislicase.data.api.model.Favorite
import com.kaanyagan.mislicase.data.repository.FavoriteRepository
import com.kaanyagan.mislicase.data.repository.LeagueAndMatchRepository
import com.kaanyagan.mislicase.data.state.FavoriteMessageState
import com.kaanyagan.mislicase.data.state.LeagueAndMatchListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val leagueAndMatchRepository: LeagueAndMatchRepository,
    private val favoriteRepository: FavoriteRepository
): ViewModel(){

    private val _leagueAndMatchListState:MutableStateFlow<LeagueAndMatchListState> = MutableStateFlow(LeagueAndMatchListState.Idle)
    val leagueAndMatchListState:StateFlow<LeagueAndMatchListState> = _leagueAndMatchListState

    // Shared Flow for managing the favorite add or remove state
    private val _favoriteAddOrRemoveState: MutableSharedFlow<Int> = MutableSharedFlow()
    val favoriteAddOrRemoveState: SharedFlow<Int> = _favoriteAddOrRemoveState

    // State Flow for managing the favorite message state
    private val _favoriteMessage: MutableStateFlow<FavoriteMessageState> = MutableStateFlow(FavoriteMessageState.Idle)
    val favoriteMessage: MutableStateFlow<FavoriteMessageState> = _favoriteMessage

    // Fetches all leagues and matches from the repository
    fun getAllLeaguesAndMatches(){
        viewModelScope.launch {
            runCatching {
                _leagueAndMatchListState.value = LeagueAndMatchListState.Loading
                val matches = leagueAndMatchRepository.getAllLeaguesAndMatches()
                if (matches.isEmpty()){
                    _leagueAndMatchListState.value = LeagueAndMatchListState.Empty
                }
                else {
                    _leagueAndMatchListState.value = LeagueAndMatchListState.Result(matches.toMutableList())
                }
            }.onFailure {
                _leagueAndMatchListState.value = LeagueAndMatchListState.Error(it)
            }
        }
    }

    // Adds or removes a match to favorites
    fun addOrRemoveFavorite(matchId:Int,position:Int) {
        viewModelScope.launch {
            runCatching {
                var isFavorite = false
                if(favoriteRepository.isMatchFavorite(matchId))
                {
                    favoriteRepository.removeFavorite(matchId)
                    _favoriteMessage.value = FavoriteMessageState.Removed
                }
                else{
                    isFavorite = true
                    val favorite = Favorite(matchId = matchId)
                    favoriteRepository.addFavorite(favorite)
                    _favoriteMessage.value = FavoriteMessageState.Added
                }
                _favoriteAddOrRemoveState.emit(position)
                if(_leagueAndMatchListState.value is LeagueAndMatchListState.Result){
                    (_leagueAndMatchListState.value as LeagueAndMatchListState.Result).matches[position].isFavorite = isFavorite
                }
            }.onFailure {
                _leagueAndMatchListState.value = LeagueAndMatchListState.Error(it)
            }
        }
    }

}