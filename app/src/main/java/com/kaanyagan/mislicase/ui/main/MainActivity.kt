package com.kaanyagan.mislicase.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.kaanyagan.mislicase.R
import com.kaanyagan.mislicase.data.state.FavoriteMessageState
import com.kaanyagan.mislicase.data.state.LeagueAndMatchListState
import com.kaanyagan.mislicase.databinding.ActivityMainBinding
import com.kaanyagan.mislicase.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding
    lateinit var adapter:LeagueAndMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initial all leagues and matches
        viewModel.getAllLeaguesAndMatches()
        // Observes changes in the league and match list state
        observeLeagueAndMatchListState()
        // Observes changes in the add or remove favorite state
        observeAddOrRemoveFavoriteState()
        // Observes changes in the favorite message state
        observeFavoriteMessageState()
    }

    private fun observeFavoriteMessageState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.favoriteMessage.collect{
                    when(it){
                        is FavoriteMessageState.Idle->{}
                        is FavoriteMessageState.Added->{
                            Toast.makeText(this@MainActivity,getString(R.string.added_favorite),Toast.LENGTH_SHORT).show()
                        }
                        is FavoriteMessageState.Removed->{
                            Toast.makeText(this@MainActivity,getString(R.string.removed_favorite),Toast.LENGTH_SHORT).show()
                        }
                        is FavoriteMessageState.Error->{
                            Toast.makeText(this@MainActivity,getString(R.string.removed_favorite),Toast.LENGTH_SHORT).show()
                        }
                        else->{}
                    }
                }
            }
        }
    }

    private fun observeLeagueAndMatchListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.leagueAndMatchListState.collect{
                    when(it){
                        is LeagueAndMatchListState.Idle->{}
                        is LeagueAndMatchListState.Loading->{
                            // In the loading state, the recyclerview is not visible, only the progress bar is visible
                            binding.rvMatch.isVisible = false
                            binding.progressBar.isVisible = true
                        }
                        is LeagueAndMatchListState.Empty->{
                            // In the loading state, the recyclerview and progress bar is not visible, only the empty list textView is visible
                            binding.progressBar.isVisible = false
                            binding.tvEl.isVisible = true
                        }
                        is LeagueAndMatchListState.Result->{
                            // In the loading state, the recyclerview is visible, progress bar is not visible
                            binding.rvMatch.isVisible = true
                            binding.progressBar.isVisible = false
                            // Adapter assigment, intent for click event to match detail activity
                            adapter = LeagueAndMatchAdapter(this@MainActivity, it.matches , this@MainActivity::onFavClick){ match, position->
                                val intent = Intent(this@MainActivity,DetailActivity::class.java)
                                intent.putExtra("match_detail",match as Parcelable)
                                startActivity(intent)
                            }
                            binding.rvMatch.adapter = adapter
                        }
                        is LeagueAndMatchListState.Error->{
                            // Displays message for error
                            binding.rvMatch.isVisible = false
                            binding.progressBar.isVisible = false
                            Snackbar.make(binding.rvMatch,getString(R.string.error), Snackbar.LENGTH_LONG).show()
                        }
                        else->{}
                    }
                }
            }
        }
    }

    // Handles click events on the favorite icon in the adapter
    private fun onFavClick(matchId: Int, position: Int) {
        viewModel.addOrRemoveFavorite(matchId,position)
    }

    private fun observeAddOrRemoveFavoriteState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.favoriteAddOrRemoveState.collect{
                    adapter.notifyItemChanged(it)
                }
            }
        }
    }
}