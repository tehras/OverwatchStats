package com.github.tehras.overwatchstats.fragments.home

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.exts.*
import com.github.tehras.overwatchstats.fragments.UserDisplayFragment
import com.github.tehras.overwatchstats.fragments.ViewPagerFragment
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.networking.*
import com.github.tehras.overwatchstats.pager.FragmentPager
import com.mancj.materialsearchbar.MaterialSearchBar

/**
 * Created by tehras on 8/22/16.
 *
 * HomeFragment
 */
class HomeFragment : ViewPagerFragment(), FragmentPager.FragmentCallback, SearchLayoutListener {
    private var executed: Boolean = false

    override fun onSearchEntered(text: CharSequence?) {
        if (executed)
            return

        val split = text.toString().split("#")
        if (split.size != 2) {
            Snackbar.make(view as View, "Invalid Format", Snackbar.LENGTH_SHORT).show()
            return
        }

        activity.showLoading()
        Runner().generalStatsRequest(OWAPIUser(split[0], split[1]), object : NetworkResponse {
            override fun onResponse(response: Response, request: Request) {
                if (response.status == Response.ResponseStatus.SUCCESS) {
                    if (response.parsingObject is OWAPIUser) {
                        Log.i(com.github.tehras.overwatchstats.exts.TAG, "parsing success")
                        (activity as AppCompatActivity).startFragment(UserDisplayFragment.create(response.parsingObject as OWAPIUser), false)
                    } else {
                        Snackbar.make(view as View, "No User Found", Snackbar.LENGTH_SHORT).show()
                    }
                }
                activity.hideLoading()
                executed = false
            }
        })
        executed = true
    }

    val TAG = "HomeFragment"

    companion object {
        fun create(): HomeFragment {
            return HomeFragment()
        }
    }

    private var searchBar: MaterialSearchBar? = null

    override fun fabPressed(fab: FloatingActionButton) {
        super.fabPressed(fab)

        searchBar = activity?.showSearchBar(fab.getCenterX(), fab.getCenterY(), this)
    }

    override fun onStop() {
        super.onStop()

        activity.hideSearchBar()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity.showTwoItemToolbar(viewPager, R.drawable.ic_search, "Favorites", R.drawable.ic_favorite, "Recent", R.drawable.ic_recent, this)
                ?.showHome(false)?.showSettings(false)
    }

    override fun getItem(position: Int): Fragment {
        Log.i(com.github.tehras.overwatchstats.exts.TAG, "getItem $position")

        when (position) {
            0 -> return FavoriteFragment.create()
            else -> return FavoriteFragment.create()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = super.onCreateView(inflater, container, savedInstanceState)

        viewPager?.adapter = FragmentPager(fragmentManager, 2, this)

        return v
    }
}