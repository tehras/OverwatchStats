package com.github.tehras.overwatchstats.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.overwatchstats.R

/**
 * Created by tehras on 8/23/16.
 */
class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesHolder>() {
    override fun onBindViewHolder(holder: FavoritesHolder?, position: Int) {
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        return FavoritesHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_champion_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return 0
    }


    class FavoritesHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}
