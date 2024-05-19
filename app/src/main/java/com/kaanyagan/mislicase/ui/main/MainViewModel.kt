package com.kaanyagan.mislicase.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaanyagan.mislicase.data.api.model.Favorite
import com.kaanyagan.mislicase.data.repository.FavoriteRepository
import com.kaanyagan.mislicase.data.repository.MatchRepository
import com.kaanyagan.mislicase.data.state.FavoriteMessageState
import com.kaanyagan.mislicase.data.state.MatchListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _favoriteAddOrRemoveState: MutableSharedFlow<Int> = MutableSharedFlow()
    val favoriteAddOrRemoveState: SharedFlow<Int> = _favoriteAddOrRemoveState

    private val _favoriteMessage: MutableStateFlow<FavoriteMessageState> = MutableStateFlow(FavoriteMessageState.Idle)
    val favoriteMessage: MutableStateFlow<FavoriteMessageState> = _favoriteMessage

    fun getAllMatches(){
        viewModelScope.launch {
            runCatching {
                _matchListState.value = MatchListState.Loading
                val matches = matchRepository.getAllMatches()
                if (matches.isEmpty()){
                    _matchListState.value = MatchListState.Empty
                }
                else {
                    _matchListState.value = MatchListState.Result(matches.toMutableList())
                }
            }.onFailure {
                _matchListState.value = MatchListState.Error(it)
            }
        }
    }

    fun addOrRemoveFavorite(i:Int,position:Int) {
        viewModelScope.launch {
            runCatching {
                var isFavorite = false
                if(favoriteRepository.isMatchFavorite(i))
                {
                    favoriteRepository.deleteFavorite(i)
                    _favoriteMessage.value = FavoriteMessageState.Removed
                }
                else{
                    isFavorite = true
                    val favorite = Favorite(matchId = i)
                    favoriteRepository.addFavorite(favorite)
                    _favoriteMessage.value = FavoriteMessageState.Added
                }
                _favoriteAddOrRemoveState.emit(position)
                if(_matchListState.value is MatchListState.Result){
                    (_matchListState.value as MatchListState.Result).matches[position].isFavorite = isFavorite
                }
            }.onFailure {
                _matchListState.value = MatchListState.Error(it)
            }
        }
    }

}