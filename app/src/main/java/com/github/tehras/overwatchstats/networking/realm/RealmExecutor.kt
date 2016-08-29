package com.github.tehras.overwatchstats.networking.realm

import com.github.tehras.overwatchstats.exts.getAll
import com.github.tehras.overwatchstats.exts.getFirst
import com.github.tehras.overwatchstats.exts.getRealmInstance
import com.github.tehras.overwatchstats.exts.singleTransaction
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.models.User
import io.realm.RealmResults

/**
 * Created by tehras on 8/25/16.
 */

fun OWAPIUser.generalStatsRequest(): OWAPIUser? {
    return getRealmInstance()?.getFirst(this.javaClass) { this.equalTo("_userKey", this@generalStatsRequest._userKey) }
}

fun User.generalStatsRequest(): User? {
    return getRealmInstance()?.getFirst(this.javaClass) { this.equalTo("_userName", this@generalStatsRequest._userName) }
}

fun getRecents(): RealmResults<OWAPIUser>? {
    val results = getRealmInstance()?.getAll(OWAPIUser::class.java)
    //clean
    cleanUpExtras(results)

    return results
}

fun cleanUpExtras(results: RealmResults<OWAPIUser>?) {
    if ((results?.isNotEmpty()) ?: false) {
        results?.forEach {
            if (it._userKey.isNullOrEmpty()) {
                getRealmInstance()?.singleTransaction { it.deleteFromRealm() }
                results.remove(it)
            }
        }
    }
}

fun getFavorites(): RealmResults<OWAPIUser>? {
    val results = getRealmInstance()?.getAll(OWAPIUser::class.java) { equalTo("favorite", true) }
    //clean
    cleanUpExtras(results)

    return results
}