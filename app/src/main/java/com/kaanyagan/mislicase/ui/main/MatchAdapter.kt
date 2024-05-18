package com.kaanyagan.mislicase.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.kaanyagan.mislicase.R
import com.kaanyagan.mislicase.data.AppDatabase
import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.data.api.model.ListItem
import com.kaanyagan.mislicase.data.api.model.ListItemType
import com.kaanyagan.mislicase.data.repository.FavoriteRepository
import com.kaanyagan.mislicase.databinding.LeagueListItemBinding
import com.kaanyagan.mislicase.databinding.MatchListItemBinding

class MatchAdapter(
    private val context: Context,
    private val matches:List<ListItem>,
    private val onFavClick:(i: Int)->Unit,
    private val onClick:(data: Data, position:Int) -> Unit): RecyclerView.Adapter<ViewHolder>() {

    class MatchViewHolder(binding:MatchListItemBinding):ViewHolder(binding.root) {
        val tvHtName = binding.tvHtName
        val tvScore = binding.tvScore
        val tvAtName = binding.tvAtName
        val ivFavorite = binding.ivFavorite
        val tvMatchStatus = binding.tvMatchStatus
    }

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
                holder as MatchViewHolder
                holder.tvHtName.text = data.ht.sn
                holder.tvAtName.text = data.at.sn
                holder.tvScore.text = data.sc.ht.r.toString() + " - " + data.sc.at.r.toString()
                holder.tvMatchStatus.text = data.sc.abbr
                holder.itemView.setOnClickListener {
                    onClick(data,position)
                }
                holder.ivFavorite.setImageResource(R.drawable.ic_unfav)
                holder.ivFavorite.setOnClickListener {
                    onFavClick(data.i)
                    return@setOnClickListener
                }
            }
            else{
                holder as LeagueViewHolder
                holder.tvLeagueName.text = this.data.to.n
                holder.ivFlag.load(data.to.flag)
            }
        }
    }

}