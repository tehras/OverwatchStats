package com.github.tehras.overwatchstats.models

import com.github.tehras.overwatchstats.models.profile.Data
import com.github.tehras.overwatchstats.networking.ParsingObject
import com.google.gson.Gson
import io.realm.RealmObject
import org.json.JSONObject

/**
 * Created by tehras on 8/18/16.
 *
 * Profile response
 */
open class Profile : ParsingObject, RealmObject() {
    var data: Data? = null

    override fun parse(response: String) {
        if (!response.isNullOrEmpty()) {
            val reader: JSONObject = JSONObject(response)

            if (reader.has("data")) {
                data = Gson().fromJson(reader.getJSONObject("data").toString(), Data::class.java)
            }
        }
    }
}
