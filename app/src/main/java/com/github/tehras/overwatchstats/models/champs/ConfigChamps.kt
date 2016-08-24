package com.github.tehras.overwatchstats.models.champs

import com.github.tehras.overwatchstats.R
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

/**
 * Created by tehras on 8/21/16.
 *
 * Config champs
 */
class ConfigChamps {
    constructor(json: String) {
        if (json.isNotBlank()) {
            val reader: JSONObject = JSONObject(json)

            if (reader.has("name"))
                name = reader.getString("name")
            if (reader.has("type"))
                type = reader.getString("type").convertToConfigType()
        }
    }

    @SerializedName("name")
    lateinit var name: String
    @SerializedName("type")
    lateinit var type: Type

    enum class Type(val color: Int) {
        @SerializedName("attack")
        ATTACK(R.color.attack),
        @SerializedName("defense")
        DEFENSE(R.color.defense),
        @SerializedName("tank")
        TANK(R.color.tank),
        @SerializedName("support")
        SUPPORT(R.color.support)
    }
}

fun String.convertToConfigType(): ConfigChamps.Type {
    ConfigChamps.Type.values().forEach {
        if (it.name.toLowerCase().equals(this))
            return it
    }

    return ConfigChamps.Type.ATTACK
}
