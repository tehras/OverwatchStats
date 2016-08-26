package com.github.tehras.overwatchstats.models.heroes

import android.util.Log
import com.github.tehras.overwatchstats.exts.TAG
import com.github.tehras.overwatchstats.models.champs.ConfigChamps
import com.github.tehras.overwatchstats.models.configChamps
import com.github.tehras.overwatchstats.networking.ParsingObject
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.Ignore
import org.json.JSONObject
import java.io.Serializable

/**
 * Created by tehras on 8/21/16.
 *
 * Hero
 */
open class Hero() : Serializable, ParsingObject, RealmObject() {
    constructor(name: String, played: Double) : this() {
        this.name = name
        this.played = played
    }

    @Ignore
    var type: ConfigChamps.Type? = null

    fun getConfirmedType(): ConfigChamps.Type {
        Log.d(TAG, "name ${name}")
        if (type == null) {
            type = ConfigChamps.Type.ATTACK //default
            if (configChamps != null) {
                configChamps!!.forEach {
                    if (it.name.equals(name, true)) {
                        type = it.type
                    }
                }
            }
        }

        return type!!
    }


    var name: String? = ""
    var played: Double? = 0.toDouble()
    @SerializedName("general_stats")
    var generalStats: HeroGeneralStats? = null
    @SerializedName("hero_stats")
    var heroStats: HeroStats? = null

    override fun parse(response: String) {
        val obj = JSONObject(response)

        if (obj.has("general_stats"))
            generalStats = Gson().fromJson(obj.getJSONObject("general_stats").toString(), HeroGeneralStats::class.java)
        if (obj.has("hero_stats"))
            heroStats = Gson().fromJson(obj.getJSONObject("hero_stats").toString(), HeroStats::class.java)
    }

    override fun toString(): String {
        return "Hero(type=$type, name=$name, played=$played, generalStats=$generalStats, heroStats=$heroStats)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Hero

        Log.d(TAG, "$name - ${other.name}")
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name?.hashCode() ?: 0
    }


}
