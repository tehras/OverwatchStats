package com.github.tehras.overwatchstats.models.profile

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by tehras on 8/18/16.
 *
 * League
 */
open class League : RealmObject() {
    @SerializedName("wins")
    var wins: String? = "0"
    @SerializedName("lost")
    var lost: String? = "0"
    @SerializedName("played")
    var played: String? = "0"
}
