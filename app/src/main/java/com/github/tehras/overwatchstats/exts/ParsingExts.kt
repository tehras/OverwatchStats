package com.github.tehras.overwatchstats.exts

import com.github.tehras.overwatchstats.models.Champion
import com.google.gson.Gson
import org.json.JSONObject

/**
 * Created by tehras on 8/18/16.
 */

fun Gson.fromJson(reader: JSONObject, name: String): Champion {
    val o = fromJson(reader.toString(), Champion::class.java)
    o.name = name

    return o
}
