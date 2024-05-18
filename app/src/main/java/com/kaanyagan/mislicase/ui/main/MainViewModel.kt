package com.kaanyagan.mislicase.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.mislicase.data.api.model.Favorite
import com.kaanyagan.mislicase.data.repository.FavoriteRepository
import com.kaanyagan.mislicase.data.repository.MatchRepository
import com.kaanyagan.mislicase.data.state.FavoriteControlState
import com.kaanyagan.mislicase.data.state.FavoriteAddOrRemoveState
import com.kaanyagan.mislicase.data.state.MatchListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val matchRepository: MatchRepository,
    private val favoriteRepository: FavoriteRepository
): ViewModel(){

    private val _matchListState:MutableStateFlow<MatchListState> = MutableStateFlow(MatchListState.Idle)
    val matchListState:StateFlow<MatchListState> = _matchListState

    private val _favoriteAddOrRemoveState: MutableStateFlow<FavoriteAddOrRemoveState> = MutableStateFlow(FavoriteAddOrRemoveState.Idle)
    val favoriteAddOrRemoveState: StateFlow<FavoriteAddOrRemoveState> = _favoriteAddOrRemoveState

    private val _favoriteStatus: MutableStateFlow<FavoriteControlState> = MutableStateFlow(FavoriteControlState.Idle)
    val favoriteStatus: StateFlow<FavoriteControlState> = _favoriteStatus

    fun getAllMatches(){
        viewModelScope.launch {
            runCatching {
                _matchListState.value = MatchListState.Loading
                val matches = matchRepository.getAllMatches()
                if (matches.isEmpty()){
                    _matchListState.value = MatchListState.Empty
                }
                else {
                    //matches.forEach { checkFavorite(it.i) }
                    _matchListState.value = MatchListState.Result(matches)
                }
            }.onFailure {
                _matchListState.value = MatchListState.Error(it)
            }
        }
    }

    fun addOrRemoveFavorite(i:Int) {
        viewModelScope.launch {
            runCatching {
                _favoriteAddOrRemoveState.value = FavoriteAddOrRemoveState.Loading
                if(favoriteRepository.checkFavorite(i)>0)
                {
                    favoriteRepository.deleteFavorite(i)
                    _favoriteAddOrRemoveState.value = FavoriteAddOrRemoveState.Remove
                    _favoriteAddOrRemoveState.value = FavoriteAddOrRemoveState.Idle
                }
                else{
                    val favorite = Favorite(matchId = 0, i=i)
                    favoriteRepository.addFavorite(favorite)
                    _favoriteAddOrRemoveState.value = FavoriteAddOrRemoveState.Add
                    _favoriteAddOrRemoveState.value = FavoriteAddOrRemoveState.Idle
                }
            }.onFailure {
                _favoriteAddOrRemoveState.value = FavoriteAddOrRemoveState.Error(it)
            }
        }
    }

}