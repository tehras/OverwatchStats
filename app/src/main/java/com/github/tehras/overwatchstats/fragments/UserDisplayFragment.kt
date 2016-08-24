package com.github.tehras.overwatchstats.fragments

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.adapters.ChampionAdapter
import com.github.tehras.overwatchstats.exts.TAG
import com.github.tehras.overwatchstats.exts.bundle
import com.github.tehras.overwatchstats.exts.initStandardLinearLayout
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
class UserDisplayFragment : BaseFragment(), HeaderProvider.Provide, NetworkResponse {
    private var adapter: ChampionAdapter? = null

    override fun onResponse(response: Response, request: Request) {
        if (response.status == Response.ResponseStatus.SUCCESS) {
            if (response.parsingObject is OWAPIUser) {
                if ((response.parsingObject as OWAPIUser).heroes != null) {
                    val user = response.parsingObject as OWAPIUser
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

    private fun updateAdapter(user: OWAPIUser) {
        //lets get the rest of the heroes...
        Log.d(TAG, "retrieved heroes")
        adapter = ChampionAdapter(user.heroes as Heroes)
        recyclerView?.adapter = adapter

        //start executing all responses
        user.heroes?.heroes?.forEach {
            if (it.generalStats == null)
                Runner().champHeroRequest(user, it, this)
        }
    }

    override fun getUser(): OWAPIUser {
        return user
    }

    private lateinit var user: OWAPIUser

    companion object Factory {
        private val BUNDLE_USER = "bundle_user"

        fun create(user: OWAPIUser): UserDisplayFragment {
            return UserDisplayFragment().bundle { putSerializable(BUNDLE_USER, user) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null)
            user = arguments?.getSerializable(BUNDLE_USER) as OWAPIUser

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

    private fun initRecyclerView(v: View?) {
        recyclerView = v?.findViewById(R.id.user_layout_recycler_view) as RecyclerView
        recyclerView?.initStandardLinearLayout(activity)

        if (user.heroes != null)
            updateAdapter(user)
    }

}
