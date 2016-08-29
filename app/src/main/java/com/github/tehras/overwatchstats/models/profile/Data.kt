package com.github.tehras.overwatchstats.models.profile

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by tehras on 8/18/16.
 *
 * Data
 */
open class Data : RealmObject() {
    @SerializedName("username")
    var username: String? = ""
    @SerializedName("level")
    var level: Int? = 0
    @SerializedName("games")
    var games: Games? = null
    @SerializedName("playtime")
    var playtime: Playtime? = null
    @SerializedName("avatar")
    var avatar: String? = ""
    @SerializedName("levelFrame")
    var levelFrame: String? = ""
    @SerializedName("star")
    var star: String? = ""

}