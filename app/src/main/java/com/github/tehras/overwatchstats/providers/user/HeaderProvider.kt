package com.github.tehras.overwatchstats.providers.user

import android.util.Log
import android.view.View
import android.widget.TextView
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.views.DataWithHint
import com.github.tehras.overwatchstats.views.UserImageView

/**
 * Created by tehras on 8/20/16.
 *
 * Provider for the UserDispayFragment
 */
class HeaderProvider(val view: View, val provider: Provide) {

    private lateinit var userImage: UserImageView
    private lateinit var userName: TextView
    private lateinit var userLevel: TextView

    private lateinit var wins: DataWithHint

    private lateinit var losses: DataWithHint
    private lateinit var timePlayed: DataWithHint

    init {
        userImage = view.findViewById(R.id.view_user_layout_user_image) as UserImageView
        userName = view.findViewById(R.id.view_user_layout_name) as TextView
        userLevel = view.findViewById(R.id.view_user_level) as TextView

        wins = view.findViewById(R.id.view_user_layout_wins) as DataWithHint
        losses = view.findViewById(R.id.view_user_layout_losses) as DataWithHint
        timePlayed = view.findViewById(R.id.view_user_layout_time_played) as DataWithHint

        val user = provider.getUser()

        populateAccountHeaderData(user)
    }

    private val TAG = "HeaderProvider"

    fun populateAccountHeaderData(user: OWAPIUser) {

        val avatar = user.generalStats?.overallStats?.avatar
        val outline = null

        Log.d(TAG, "${user.generalStats?.battletag}")

        userImage.populateImages(avatar, outline)
        userName.text = user.generalStats?.battletag
        userLevel.text = "Level ${user.generalStats?.overallStats?.level ?: 0}"

        timePlayed.value.text = "${user.generalStats?.gameStats?.timePlayed?.toInt() ?: 0} hours"
        wins.value.text = (user.generalStats?.overallStats?.wins ?: "N/A").toString()
        losses.value.text = (user.generalStats?.overallStats?.losses ?: "N/A").toString()

    }

    interface Provide {
        fun getUser(): OWAPIUser
    }
}
