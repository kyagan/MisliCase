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
import com.kaanyagan.mislicase.data.state.MatchListState
import com.kaanyagan.mislicase.databinding.ActivityMainBinding
import com.kaanyagan.mislicase.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding
    lateinit var adapter:MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAllMatches()
        observeMatchListState()
        observeAddOrRemoveFavoriteState()
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
                    }
                }
            }
        }
    }

    private fun observeMatchListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.matchListState.collect{
                    when(it){
                        is MatchListState.Idle->{}
                        is MatchListState.Loading->{
                            binding.rvMatch.isVisible = false
                            binding.progressBar.isVisible = true
                        }
                        is MatchListState.Empty->{
                            binding.progressBar.isVisible = false
                            binding.tvEl.isVisible = true
                        }
                        is MatchListState.Result->{
                            binding.rvMatch.isVisible = true
                            binding.progressBar.isVisible = false
                            adapter = MatchAdapter(this@MainActivity, it.matches , this@MainActivity::onFavClick){match,position->
                                val intent = Intent(this@MainActivity,DetailActivity::class.java)
                                intent.putExtra("match_detail",match as Parcelable)
                                startActivity(intent)
                            }
                            binding.rvMatch.adapter = adapter
                        }
                        is MatchListState.Error->{
                            binding.rvMatch.isVisible = false
                            binding.progressBar.isVisible = false
                            Snackbar.make(binding.rvMatch,getString(R.string.error), Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

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