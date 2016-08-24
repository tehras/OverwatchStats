package com.github.tehras.overwatchstats.models.heroes

import android.util.Log
import com.github.tehras.overwatchstats.exts.TAG
import com.github.tehras.overwatchstats.networking.ParsingObject
import org.json.JSONObject
import java.io.Serializable
import java.util.*

/**
 * Created by tehras on 8/21/16.
 *
 * Heroes
 */
class Heroes : Serializable, ParsingObject {
    override fun parse(response: String) {

    }

    @SuppressWarnings("UNUSED")
    constructor(json: String) {
        val reader = JSONObject(json)

        if (reader.has("heroes")) {
            val obj = reader.getJSONObject("heroes")

            obj.keys().forEach {
                val hero = obj.getDouble(it)

                if (heroes == null)
                    heroes = ArrayList()

                heroes!!.add(Hero(it, hero))
            }

            //now sort
            if (heroes != null)
                Collections.sort(heroes) { l, r -> r.played!!.compareTo(l.played!!.toDouble()) }

            Log.d(TAG, "finished parsing heroes - ${heroes?.size}")
        }
    }

    var heroes: ArrayList<Hero>? = null
}
