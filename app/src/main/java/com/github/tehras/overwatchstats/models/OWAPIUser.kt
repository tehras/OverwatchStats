package com.github.tehras.overwatchstats.models

import com.github.tehras.overwatchstats.models.general_stats.GeneralStats
import com.github.tehras.overwatchstats.models.heroes.Heroes
import com.github.tehras.overwatchstats.networking.ParsingObject
import com.google.gson.Gson
import org.json.JSONObject
import java.io.Serializable

/**
 * Created by tehras on 8/20/16.
 *
 * OWAPIUser
 */
class OWAPIUser(val username: String, val tag: String) : ParsingObject, Serializable {

    var generalStats: GeneralStats? = null
    var heroes: Heroes? = null

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