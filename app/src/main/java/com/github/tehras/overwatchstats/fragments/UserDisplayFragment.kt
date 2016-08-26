package com.github.tehras.overwatchstats.fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.adapters.ChampionAdapter
import com.github.tehras.overwatchstats.exts.*
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.models.heroes.Hero
import com.github.tehras.overwatchstats.models.heroes.Heroes
import com.github.tehras.overwatchstats.networking.*
import com.github.tehras.overwatchstats.providers.user.HeaderProvider

/**
 * Created by tehras on 8/18/16.
 *
 * User display
 */
class UserDisplayFragment : ViewPagerFragment(), HeaderProvider.Provide, NetworkResponse {
    private var adapter: ChampionAdapter? = null

    override fun onStart() {
        super.onStart()

        updateToolbar()
    }

    private fun updateToolbar() {
        var icon = R.drawable.ic_favorite
        if (user.favorite)
            icon = R.drawable.ic_close_white

        activity.showTwoItemToolbar(viewPager, icon, "Favorites", R.drawable.ic_favorite, "Recent", R.drawable.ic_recent, this)
                ?.showHome(true)?.showSettings(true)
    }

    override fun fabPressed(fab: FloatingActionButton) {
        super.fabPressed(fab)

        //add to favorites
        val fav = user.favorite
        getRealmInstance()?.singleTransaction {
            user.favorite = !fav
        }

        updateToolbar()
        if (view != null) {
            if (!fav)
                Snackbar.make(view!!, "Added to favorites", Snackbar.LENGTH_SHORT).show()
            else
                Snackbar.make(view!!, "Removed from favorites", Snackbar.LENGTH_SHORT).show()
        }
    }


    override fun onResponse(response: Response, request: Request) {
        if (response.status == Response.ResponseStatus.SUCCESS) {
            if (response.parsingObject is OWAPIUser) {
                if ((response.parsingObject as OWAPIUser).heroes != null) {
                    (response.parsingObject as OWAPIUser).heroes?.copyField() {
                        user.heroes = this
                    }

                    updateAdapter(user)
                }
            } else if (response.parsingObject is Hero) {
                adapter?.updateHero(response.parsingObject as Hero)
            }
        } else {
            Log.d(TAG, "error retrieving")
            //todo error
        }
    }

    override fun onDbResponse(obj: Any?) {
        super.onDbResponse(obj)

        if (obj is OWAPIUser) {

        }
    }

    private fun updateAdapter(user: OWAPIUser) {
        //lets get the rest of the heroes...
        Log.d(TAG, "retrieved heroes")
        adapter = ChampionAdapter(user.heroes as Heroes)
        recyclerView?.adapter = adapter

        //start executing all responses
        if (needsRefresh) {
            needsRefresh = false
            user.heroes?.heroes?.forEach {
                Runner().champHeroRequest(user, getNewHero(it) ?: Hero(it.name ?: "", it.played ?: 0.toDouble()), this)
            }
        }
    }

    private fun getNewHero(h: Hero): Hero? {
        return Hero(h.name ?: "", h.played ?: 0.toDouble())
    }

    override fun getUser(): OWAPIUser {
        return user
    }

    private lateinit var user: OWAPIUser

    companion object Factory {
        private val BUNDLE_KEY = "bundle_user"

        fun create(user: OWAPIUser): UserDisplayFragment {
            user.addToRealm()
            return UserDisplayFragment().bundle { putString(BUNDLE_KEY, user._userKey) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null)
            user = getRealmInstance()?.getFirst(OWAPIUser::class.java, {
                this.equalTo("_userKey", arguments.getString(BUNDLE_KEY))
            }) ?: OWAPIUser("", "")

        //lets get the champions..
        if (user.heroes == null)
            Runner().generalHeroesRequest(user, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_user_layout, container, false)

        HeaderProvider(v, this)
        initRecyclerView(v)

        return v
    }

    private var recyclerView: RecyclerView? = null
    private var needsRefresh: Boolean = true

    private fun initRecyclerView(v: View?) {
        recyclerView = v?.findViewById(R.id.user_layout_recycler_view) as RecyclerView
        recyclerView?.initStandardLinearLayout(activity)

        if (user.heroes != null)
            updateAdapter(user)
    }

}
