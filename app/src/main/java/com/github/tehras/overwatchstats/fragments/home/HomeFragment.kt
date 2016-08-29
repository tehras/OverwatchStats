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
import com.github.tehras.overwatchstats.adapters.FavoriteOrRecent
import com.github.tehras.overwatchstats.exts.*
import com.github.tehras.overwatchstats.fragments.ViewPagerFragment
import com.github.tehras.overwatchstats.fragments.user.UserFragment
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.networking.*
import com.github.tehras.overwatchstats.networking.realm.getRecents
import com.github.tehras.overwatchstats.pager.FragmentPager
import com.mancj.materialsearchbar.MaterialSearchBar
import io.realm.RealmResults
import java.util.*

/**
 * Created by tehras on 8/22/16.
 *
 * HomeFragment
 */
class HomeFragment : ViewPagerFragment(), FragmentPager.FragmentCallback, SearchLayoutListener {
    private var executed: Boolean = false

    override fun onBackPressed(): Boolean {
        if (searchBar?.isShown ?: false) {
            searchBar?.hide()
            return true
        }

        return super.onBackPressed()
    }

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
                        (response.parsingObject as OWAPIUser).addToRealm()
                        startUserFragment(response.parsingObject as OWAPIUser, false)
                    } else {
                        Snackbar.make(view as View, "No User Found", Snackbar.LENGTH_SHORT).show()
                    }
                } else {
                    Snackbar.make(view as View, "No User Found", Snackbar.LENGTH_SHORT).show()
                }
                activity.hideLoading()
                executed = false
            }

            override fun onDbResponse(obj: Any?) {
                super.onDbResponse(obj)

                if (obj is OWAPIUser) {
                    startUserFragment(obj, true)
                    activity.hideLoading()
                }
            }
        })
        executed = true
        executed = true
    }

    fun startUserFragment(user: OWAPIUser, fromDb: Boolean) {
        activity.hideLoading()
        (activity as AppCompatActivity).startFragment(UserFragment.create(user, fromDb), false)
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
        searchBar?.lastSuggestions = getRecentHistorySuggestionsString()
        searchBar?.enableSearch() // suggestions

    }

    private var lastRecent: RealmResults<OWAPIUser>? = null

    private fun getRecentHistorySuggestionsString(): ArrayList<String> {
        lastRecent = getRecents()

        val suggestions = ArrayList<String>()
        lastRecent?.forEach { suggestions.add("${it._userKey}") }

        return suggestions
    }

    override fun onStop() {
        super.onStop()

        activity.hideSearchBar()
    }

    override fun onStart() {
        super.onStart()

        activity.showTwoItemToolbar(viewPager, R.drawable.ic_search, "Favorites", R.drawable.ic_favorite, "Recent", R.drawable.ic_recent, this)
                ?.showHome(false)?.showSettings(false)
    }

    override fun getItem(position: Int): Fragment {
        Log.i(com.github.tehras.overwatchstats.exts.TAG, "getItem $position")

        when (position) {
            0 -> return FavoriteOrRecentFragment.create(FavoriteOrRecent.FAVORITE)
            else -> return FavoriteOrRecentFragment.create(FavoriteOrRecent.RECENT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = super.onCreateView(inflater, container, savedInstanceState)

        Log.d(TAG, "HomeFragment on CreateView")
        viewPager?.adapter = FragmentPager(childFragmentManager, 2, this)
        viewPager?.setCurrentItem(0, false)

        return v
    }
}