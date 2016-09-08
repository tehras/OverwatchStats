package com.github.tehras.overwatchstats.fragments.user

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.exts.*
import com.github.tehras.overwatchstats.fragments.ViewPagerFragment
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.pager.FragmentPager

/**
 * Created by tehras on 8/28/16.
 *
 * User Fragment
 */
class UserFragment : ViewPagerFragment(), FragmentPager.FragmentCallback {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return QuickPlayFragment.create(userKey ?: "", fromDb ?: false)
            else -> return CompetativePlayFragment.create()
        }
    }

    companion object Factory {
        private val BUNDLE_KEY = "bundle_user"
        private val BUNDLE_FROM_DB = "bundle_from_db"

        fun create(user: OWAPIUser, fromDb: Boolean): UserFragment {
            user.addToRealm()
            return UserFragment().bundle {
                putString(BUNDLE_KEY, user._userKey)
                putBoolean(BUNDLE_FROM_DB, fromDb)
            }
        }
    }

    private var userKey: String? = null
    private var fromDb: Boolean? = false

    private var user: OWAPIUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            userKey = arguments.getString(BUNDLE_KEY)
            fromDb = arguments.getBoolean(BUNDLE_FROM_DB)

            user = getRealmInstance()?.getFirst(OWAPIUser::class.java, {
                this.equalTo("_userKey", userKey)
            }) ?: OWAPIUser("", "")
        }
    }

    override fun fabPressed(fab: FloatingActionButton) {
        super.fabPressed(fab)

        //add to favorites
        val fav = user?.favorite ?: false
        getRealmInstance()?.singleTransaction {
            user?.favorite = !fav
        }

        updateToolbar()
        if (view != null) {
            if (!fav)
                Snackbar.make(view!!, "Added to favorites", Snackbar.LENGTH_SHORT).show()
            else
                Snackbar.make(view!!, "Removed from favorites", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        viewPager?.adapter = FragmentPager(childFragmentManager, 2, this)

        return view
    }

    override fun onStart() {
        super.onStart()

        updateToolbar()
    }

    private fun updateToolbar() {
        var icon = R.drawable.ic_favorite
        if (user?.favorite ?: true)
            icon = R.drawable.ic_close_white

        activity.showTwoItemToolbar(viewPager, icon, "Competitive", R.drawable.ic_competative, "Quick Play", R.drawable.ic_quick_play, this)
                ?.showHome(true)?.showSettings(true)
    }

}
