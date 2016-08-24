package com.github.tehras.overwatchstats.models

import android.util.Log
import com.github.tehras.overwatchstats.exts.fromJson
import com.github.tehras.overwatchstats.networking.ParsingObject
import com.google.gson.Gson
import org.json.JSONObject
import java.io.Serializable
import java.util.*

/**
 * Created by tehras on 8/17/16.
 *
 * User Parsing Object
 */
class User : ParsingObject, Serializable {
    private val TAG = "ParsingObject"

    var champions: ArrayList<Champion>? = null
    var profile: Profile? = null

    override fun parse(response: String) {
        Log.i(TAG, "response - " + response)
        if (!response.isNullOrEmpty()) {
            val reader = JSONObject(response)

            val keys = reader.keys()
            while (keys.hasNext()) {
                val key = keys.next()

                if (champions == null)
                    champions = ArrayList()

                champions!!.add(Gson().fromJson(reader.getJSONObject(key), key))
            }
        }
    }

}
