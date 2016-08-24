package com.github.tehras.overwatchstats.fragments.home

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.adapters.FavoritesAdapter
import com.github.tehras.overwatchstats.exts.initStandardLinearLayout
import com.github.tehras.overwatchstats.fragments.BaseFragment

/**
 * Created by tehras on 8/22/16.
 */
class FavoriteFragment : BaseFragment() {
    companion object {
        fun create(): FavoriteFragment = FavoriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_favorites_layout, container, false)

        val recView = v.findViewById(R.id.favorites_recycler_view) as RecyclerView
        recView.initStandardLinearLayout(activity)

        recView.adapter = FavoritesAdapter()

        return v
    }
}