package com.kaanyagan.mislicase.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.kaanyagan.mislicase.R
import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.data.api.model.ListItem
import com.kaanyagan.mislicase.data.api.model.ListItemType
import com.kaanyagan.mislicase.databinding.LeagueListItemBinding
import com.kaanyagan.mislicase.databinding.MatchListItemBinding

// RecyclerView adapter for displaying leagues and matches in a list
class LeagueAndMatchAdapter(
    private val context: Context,
    private val matches:List<ListItem>,
    private val onFavClick:(matchId: Int, position:Int)->Unit,
    private val onClick:(data: Data, position:Int) -> Unit): RecyclerView.Adapter<ViewHolder>() {

    // View holder for displaying match items
    class MatchViewHolder(binding:MatchListItemBinding):ViewHolder(binding.root) {
        val tvHtName = binding.tvHtName
        val tvScore = binding.tvScore
        val tvAtName = binding.tvAtName
        val ivFavorite = binding.ivFavorite
        val tvMatchStatus = binding.tvMatchStatus
    }

    // View holder for displaying league items
    class LeagueViewHolder(binding: LeagueListItemBinding):ViewHolder(binding.root){
        val tvLeagueName = binding.tvLeagueName
        val ivFlag = binding.ivFlag
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == ListItemType.MATCH.type)
            MatchViewHolder(MatchListItemBinding.inflate(LayoutInflater.from(context),parent, false))
        else
            LeagueViewHolder(LeagueListItemBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemViewType(position: Int): Int {
        return matches[position].listItemType.type
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(matches[position]){
            if(listItemType == ListItemType.MATCH){
                // Assigns holder to MatchViewHolder
                holder as MatchViewHolder

                // Sets favorite icon based on the match's favorite status
                if (isFavorite){
                    holder.ivFavorite.setImageResource(R.drawable.ic_fav)
                }
                else{
                    holder.ivFavorite.setImageResource(R.drawable.ic_unfav)
                }

                // Displays Home Team name
                holder.tvHtName.text = data.ht.sn
                // Displays Away Team name
                holder.tvAtName.text = data.at.sn
                // Displays match score
                holder.tvScore.text = data.sc.ht.r.toString() + " - " + data.sc.at.r.toString()
                // Displays match status(finished matches)
                holder.tvMatchStatus.text = data.sc.abbr

                // Handles click events on the match item
                holder.itemView.setOnClickListener {
                    onClick(data,position)
                }
                // Handles click events on the favorite icon
                holder.ivFavorite.setOnClickListener {
                    onFavClick(data.i,position)
                    return@setOnClickListener
                }
            }
            else{
                // Assigns holder to LeagueViewHolder
                holder as LeagueViewHolder
                // Displays League Name
                holder.tvLeagueName.text = this.data.to.n
                // Displays League Flag
                holder.ivFlag.load(data.to.flag)
            }
        }
    }

}