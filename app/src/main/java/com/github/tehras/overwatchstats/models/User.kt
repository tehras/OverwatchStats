package com.github.tehras.overwatchstats.models

import android.util.Log
import com.github.tehras.overwatchstats.exts.fromJson
import com.github.tehras.overwatchstats.networking.ParsingObject
import com.google.gson.Gson
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import org.json.JSONObject

/**
 * Created by tehras on 8/17/16.
 *
 * User Parsing Object
 */
open class User(var username: String, var tag: String) : ParsingObject, RealmObject() {
    constructor() : this("", "")

    @PrimaryKey
    var _userName = "$username#$tag"

    @Ignore
    private val TAG = "ParsingObject"

    var champions: RealmList<Champion>? = null
    var profile: Profile? = null
    var favorite: Boolean = false

    override fun parse(response: String) {
        Log.i(TAG, "response - " + response)
        if (!response.isNullOrEmpty()) {
            val reader = JSONObject(response)

            if (reader.has("data")) {
                profile = Gson().fromJson(reader.toString(), Profile::class.java)
            } else {
                val keys = reader.keys()
                while (keys.hasNext()) {
                    val key = keys.next()

                    if (champions == null)
                        champions = RealmList()

                    val champ = Gson().fromJson(reader.getJSONObject(key), key)
                    champ.lastUpdated = System.currentTimeMillis()

                    champions!!.add(champ)
                }
            }
        }
    }

}
