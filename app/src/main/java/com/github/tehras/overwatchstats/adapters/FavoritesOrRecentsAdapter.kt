package com.github.tehras.overwatchstats.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.exts.*
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.models.User
import com.github.tehras.overwatchstats.views.DataWithHint
import com.squareup.picasso.Picasso
import io.realm.RealmResults
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.Serializable

/**
 * Created by tehras on 8/23/16.
 *
 * Favorites Adapter
 */
class FavoritesOrRecentsAdapter(val clickListener: OnClickListener, val favoriteOrRecent: FavoriteOrRecent) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var users: RealmResults<OWAPIUser>? = null
    private var loaded: Boolean = false

    fun updateData(users: RealmResults<OWAPIUser>?) {
        this.loaded = true
        this.users = users

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is FavoritesHolder) {
            holder.populate(users?.get(position - 1))
            holder.itemView.findViewById(R.id.user_overall_icon).setOnClickListener({ clickListener.onClick(user = users?.get(position - 1)) })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            LOADING -> return parent.ladingHolder()
            TITLE -> return parent.titleHolder(if (favoriteOrRecent == FavoriteOrRecent.FAVORITE) R.string.favorites_favorites_title else R.string.favorites_recent_title, if (favoriteOrRecent == FavoriteOrRecent.FAVORITE) R.drawable.ic_favorite_black else R.drawable.ic_recent_black)
            NO_FAVORITES -> return parent.errorHolder((if (favoriteOrRecent == FavoriteOrRecent.FAVORITE) R.string.favorites_no_favorites_added else R.string.favorites_no_recent_added))
            else -> return FavoritesHolder(parent.inflate(R.layout.adapter_user_layout))
        }
    }

    override fun getItemCount(): Int {
        if (users?.isEmpty() ?: true)
            return 1
        return (users?.size ?: 1) + 1
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> {
                if (users?.isEmpty() ?: true) {
                    if (loaded) return NO_FAVORITES else return LOADING
                } else
                    return TITLE
            }
            else -> return FAVORITE
        }
    }

    private val NO_FAVORITES = 0
    private val LOADING = 1
    private val FAVORITE = 2
    private val TITLE = 3

    class FavoritesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var icon: ImageView
        lateinit var userName: TextView
        lateinit var userLevel: TextView
        lateinit var userWinLoss: DataWithHint
        lateinit var userWinPercentage: DataWithHint

        init {
            icon = itemView.findViewById(R.id.user_icon) as ImageView
            userName = itemView.findViewById(R.id.user_name) as TextView
            userLevel = itemView.findViewById(R.id.user_rank) as TextView
            userWinLoss = itemView.findViewById(R.id.user_win_loss) as DataWithHint
            userWinPercentage = itemView.findViewById(R.id.user_win_rate) as DataWithHint
        }

        fun populate(owapiUser: OWAPIUser?) {
            Picasso.with(itemView.context).load(owapiUser?.generalStats?.overallStats?.avatar).transform(RoundedCornersTransformation(12, 0)).into(icon)
            userName.text = "${owapiUser?.username + "#" + owapiUser?.tag}"
            userLevel.text = "Level ${(owapiUser?.generalStats?.overallStats?.prestige?.times(100) ?: 0).plus((owapiUser?.generalStats?.overallStats?.level ?: 0.toInt())).toString()}"
            userWinLoss.value.text = winLoss(owapiUser)
            userWinPercentage.value.text = "${owapiUser?.generalStats?.overallStats?.winRate ?: "0"}% "
        }

        private fun winLoss(owapiUser: OWAPIUser?): CharSequence? {
            return "${owapiUser?.generalStats?.overallStats?.wins ?: 0}W / ${owapiUser?.generalStats?.overallStats?.losses ?: 0}L"
        }

    }
}

enum class FavoriteOrRecent : Serializable {
    RECENT, FAVORITE
}

interface OnClickListener {
    fun onClick(user: OWAPIUser?)
}
