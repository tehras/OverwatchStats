package com.github.tehras.overwatchstats.networking

import android.os.AsyncTask
import com.github.tehras.overwatchstats.models.OWAPIUser
import com.github.tehras.overwatchstats.models.heroes.Hero
import com.github.tehras.overwatchstats.networking.realm.generalStatsRequest

/**
 * Created by tehras on 8/17/16.
 *
 * Helper
 */
fun Runner.championsRequest(username: String, tag: String, parsingObject: ParsingObject, callbackObj: NetworkResponse) {
    val request = Request(Request.Type.GET, baseUrl + system + region + formulateUser(username, tag) + quickPlay + String.format(hero, allHeroes()), parsingObject, callbackObj)

    this.execute(request)
}

fun Runner.usernameSearchRequest(username: String, tag: String, parsingObject: ParsingObject, callbackObj: NetworkResponse) {
    val request = Request(Request.Type.GET, baseUrl + system + region + formulateUser(username, tag) + profile, parsingObject, callbackObj)

    this.execute(request)
}

fun Runner.generalStatsRequest(user: OWAPIUser, networkResponse: NetworkResponse) {
    val foundUser = user.generalStatsRequest()
    if (foundUser != null) {
        networkResponse.onDbResponse(foundUser)
    } else {
        this.execute(Request(Request.Type.GET, baseOWAPIurl + formulateUser(user.username, user.tag) + generalStatsUrl, user, networkResponse))
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
    return "Lucio%2CSoldier76/"
}

fun formulateUser(username: String, tag: String): Any? {
    return "$username-$tag/"
}
