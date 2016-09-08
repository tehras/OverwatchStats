package com.github.tehras.overwatchstats.networking

import android.os.AsyncTask
import android.util.Log
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.models.User
import com.github.tehras.overwatchstats.models.configChamps
import com.github.tehras.overwatchstats.models.heroes.Hero
import com.github.tehras.overwatchstats.networking.realm.generalStatsRequest

/**
 * Created by tehras on 8/17/16.
 *
 * Helper
 */
fun Runner.championsRequest(user: User, callbackObj: NetworkResponse) {
    val request = Request(Request.Type.GET, baseUrl + system + region + formulateUser(user.username, user.tag) + quickPlay + allHeroes(), User(user.username, user.tag), callbackObj)

    this.execute(request)
}

fun Runner.usernameSearchRequest(username: String, tag: String, callbackObj: NetworkResponse) {
    val user = User(username, tag)
    val foundUser = user.generalStatsRequest()
    if (foundUser != null) {
        callbackObj.onDbResponse(user)
    } else
        this.execute(Request(Request.Type.GET, baseUrl + system + region + formulateUser(username, tag) + profile, user, callbackObj))
}

fun Runner.generalStatsRequest(user: OWAPIUser, networkResponse: NetworkResponse) {
    this.generalStatsRequest(user, false, networkResponse)
}

fun Runner.generalStatsRequest(user: OWAPIUser, skipDb: Boolean, networkResponse: NetworkResponse) {
    var foundUser: OWAPIUser? = null
    if (!skipDb)
        foundUser = user.generalStatsRequest()
    if (foundUser != null) {
        networkResponse.onDbResponse(foundUser)
    } else {
        Log.d(TAG, "username ${user.username} ${user.tag}")
        this.execute(Request(Request.Type.GET, baseOWAPIurl + formulateUser(user.username, user.tag) + generalStatsUrl, OWAPIUser(user.username, user.tag), networkResponse))
    }
}

fun Runner.generalHeroesRequest(user: OWAPIUser, networkResponse: NetworkResponse) {
    this.execute(Request(Request.Type.GET, baseOWAPIurl + formulateUser(user.username, user.tag) + generalHeroesUrl, OWAPIUser(), networkResponse))
}

fun Runner.champHeroRequest(user: OWAPIUser, hero: Hero, networkResponse: NetworkResponse) {
    this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Request(Request.Type.GET, baseOWAPIurl + formulateUser(user.username, user.tag) + generalChampUrl.format(hero.name), hero, networkResponse))
}

//fun Runner.generalChampionsRequest()

fun allHeroes(): String {
    var allChamps: String = ""
    configChamps?.forEachIndexed { i, configChamp -> allChamps += configChamp.name + if (i == (configChamps?.size?.minus(1))) "/" else "," }

    return "hero/$allChamps"
}

fun formulateUser(username: String, tag: String): String {
    return "$username-$tag"
}
