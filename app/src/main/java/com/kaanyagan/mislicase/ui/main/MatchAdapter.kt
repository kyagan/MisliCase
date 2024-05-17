package com.kaanyagan.mislicase.ui.main

import android.content.Context
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kaanyagan.mislicase.databinding.MatchListItemBinding

class MatchAdapter(private val context: Context, private val matches:List<Data>): RecyclerView.Adapter<MatchAdapter.MyViewHolder>() {
    class MyViewHolder(binding:MatchListItemBinding):ViewHolder(binding.root) {
        val tvHtName = binding.tvHtName
        val tvScore = binding.tvScore
        val tvAtName = binding.tvAtName

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MatchListItemBinding.inflate(LayoutInflater.from(context),parent, false)
        )
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(matches[position]){
            holder.tvHtName.text = ht.sn
            holder.tvAtName.text = at.sn
            holder.tvScore.text = sc.ht.r.toString() + " - " + sc.at.r.toString()
        }
    }

}