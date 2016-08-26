package com.github.tehras.overwatchstats.networking.realm

import android.content.Context
import com.github.tehras.overwatchstats.exts.getFirst
import com.github.tehras.overwatchstats.exts.getRealmInstance
import com.github.tehras.overwatchstats.models.OWAPIUser

/**
 * Created by tehras on 8/25/16.
 */

fun OWAPIUser.generalStatsRequest(): OWAPIUser? {
    return getRealmInstance()?.getFirst(this.javaClass) { this.equalTo("_userKey", this@generalStatsRequest._userKey) }
}
