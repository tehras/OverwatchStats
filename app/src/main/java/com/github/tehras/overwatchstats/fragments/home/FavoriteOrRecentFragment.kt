package com.github.tehras.overwatchstats.fragments.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.adapters.FavoriteOrRecent
import com.github.tehras.overwatchstats.adapters.FavoritesOrRecentsAdapter
import com.github.tehras.overwatchstats.adapters.OnClickListener
import com.github.tehras.overwatchstats.exts.bundle
import com.github.tehras.overwatchstats.exts.initStandardLinearLayout
import com.github.tehras.overwatchstats.exts.startFragment
import com.github.tehras.overwatchstats.fragments.BaseFragment
import com.github.tehras.overwatchstats.fragments.user.UserFragment
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.networking.realm.getFavorites
import com.github.tehras.overwatchstats.networking.realm.getRecents

/**
 * Created by tehras on 8/22/16.
 *
 * Favorite Fragment
 */
class FavoriteOrRecentFragment : BaseFragment() {
    companion object {
        val BUNDLE_FAVORITE_OR_RECENT = "key_bundle_fav_or_recent"

        fun create(favoriteOrRecent: FavoriteOrRecent): FavoriteOrRecentFragment {
            return FavoriteOrRecentFragment().bundle { putSerializable(BUNDLE_FAVORITE_OR_RECENT, favoriteOrRecent) }
        }
    }

    private var favoriteOrRecent: FavoriteOrRecent = FavoriteOrRecent.RECENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            favoriteOrRecent = arguments.getSerializable(BUNDLE_FAVORITE_OR_RECENT) as FavoriteOrRecent
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_favorites_or_recents_layout, container, false)

        val recView = v.findViewById(R.id.favorites_or_recents_recycler_view) as RecyclerView
        recView.initStandardLinearLayout(activity)

        val adapter = FavoritesOrRecentsAdapter(object : OnClickListener {
            override fun onClick(user: OWAPIUser?) {
                if (user != null)
                    (activity as AppCompatActivity).startFragment(UserFragment.create(user, true), false)
            }
        }, favoriteOrRecent)
        recView.adapter = adapter
        adapter.updateData((if (favoriteOrRecent == FavoriteOrRecent.RECENT) getRecents() else getFavorites()))

        return v
    }
}