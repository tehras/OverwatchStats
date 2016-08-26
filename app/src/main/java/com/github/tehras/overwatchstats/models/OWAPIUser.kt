package com.github.tehras.overwatchstats.models

import com.github.tehras.overwatchstats.models.general_stats.GeneralStats
import com.github.tehras.overwatchstats.models.heroes.Heroes
import com.github.tehras.overwatchstats.networking.ParsingObject
import com.google.gson.Gson
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.json.JSONObject
import java.io.Serializable

/**
 * Created by tehras on 8/20/16.
 *
 * OWAPIUser
 */
open class OWAPIUser(var username: String, var tag: String) : ParsingObject, Serializable, RealmObject() {

    constructor() : this("", "")

    @PrimaryKey
    var _userKey = username + tag
    var generalStats: GeneralStats? = null
    var heroes: Heroes? = null
    var favorite: Boolean = false

    override fun parse(response: String) {
        if (response.isNotEmpty()) {
            val reader = JSONObject(response)
            if (reader.has("heroes"))
                heroes = Heroes(response)
            else
                generalStats = Gson().fromJson(response, GeneralStats::class.java)


        }
    }

}