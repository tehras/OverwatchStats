package com.github.tehras.overwatchstats.fragments.user

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.activetouch.touchlisteners.ActiveTouchBehavior
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.adapters.ChampionAdapter
import com.github.tehras.overwatchstats.exts.*
import com.github.tehras.overwatchstats.fragments.BaseFragment
import com.github.tehras.overwatchstats.models.GameType
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.models.heroes.Hero
import com.github.tehras.overwatchstats.models.heroes.Heroes
import com.github.tehras.overwatchstats.networking.*
import com.github.tehras.overwatchstats.providers.user.HeaderProvider
import com.github.tehras.overwatchstats.views.HeroLayoutView

/**
 * Created by tehras on 8/18/16.
 *
 * User display
 */
class QuickPlayFragment : BaseFragment(), HeaderProvider.Provide, NetworkResponse, ActiveTouchBehavior.ActiveTouchViewCallback {

    override fun getView(o: Any?): View {
        if (heroLayout == null)
            heroLayout = HeroLayoutView(activity)

        if (o is Hero)
            return heroLayout!!.populate(hero = o, user = user)
        else
            return heroLayout!!
    }

    override fun getUser(): OWAPIUser {
        return user
    }

    override fun getGameType(): GameType {
        return GameType.QUICK // todo later
    }

    private var adapter: ChampionAdapter? = null

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

    var heroLayout: HeroLayoutView? = null

    fun addLongHolder(v: View?, hero: Hero) {
        ActiveTouchBehavior.Builder(v!!, this.activity.findViewById(R.id.fragment_container) as ViewGroup)
                .setContentView(this)
                .setObject(hero)
                .build(this.activity)
    }

    private fun updateAdapter(user: OWAPIUser) {
        //lets get the rest of the heroes...
        Log.d(TAG, "retrieved heroes")
        adapter = ChampionAdapter(user.heroes as Heroes) { h ->
            addLongHolder(this, h)
        }

        recyclerView?.adapter = adapter

        //start executing all responses
        if (needsRefresh) {
            needsRefresh = false
            user.heroes?.heroes?.forEach {
                Log.d(TAG, "updated - ${it.updated}")
                if (!(it.played?.isZero() ?: true) && !it.updated)
                    Runner().champHeroRequest(user, getNewHero(it) ?: Hero(it.name ?: "", it.played ?: 0.toDouble()), this)
            }
        }
    }

    private fun getNewHero(h: Hero): Hero? {
        return Hero(h.name ?: "", h.played ?: 0.toDouble())
    }

    private lateinit var user: OWAPIUser

    companion object Factory {
        private val BUNDLE_KEY = "bundle_user"
        private val BUNDLE_FROM_DB = "bundle_from_db"

        fun create(userKey: String, fromDb: Boolean): QuickPlayFragment {
            return QuickPlayFragment().bundle {
                putString(BUNDLE_KEY, userKey)
                putBoolean(BUNDLE_FROM_DB, fromDb)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            user = getRealmInstance()?.getFirst(OWAPIUser::class.java, {
                this.equalTo("_userKey", arguments.getString(BUNDLE_KEY))
            }) ?: OWAPIUser("", "")

            val fromDb = arguments.getBoolean(BUNDLE_FROM_DB)

            if (fromDb) {
                Runner().generalStatsRequest(user, true, object : NetworkResponse {
                    override fun onResponse(response: Response, request: Request) {
                        if (response.status == Response.ResponseStatus.SUCCESS && response.parsingObject is OWAPIUser && (response.parsingObject as OWAPIUser).generalStats != null) {
                            (response.parsingObject as OWAPIUser).generalStats?.copyField() {
                                user.generalStats = this
                            }
                            provider?.populateAccountHeaderData() //refresh
                        }
                    }
                })
            }


            //lets get the champions..
            if (user.heroes == null)
                Runner().generalHeroesRequest(user, this)
        }
    }

    private var provider: HeaderProvider? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_user_layout, container, false)

        provider = HeaderProvider(v, this)

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
