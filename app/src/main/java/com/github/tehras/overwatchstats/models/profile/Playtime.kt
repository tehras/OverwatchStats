package com.github.tehras.overwatchstats.models.profile

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by tehras on 8/18/16.
 *
 * Playtime
 */
open class Playtime : RealmObject() {
    @SerializedName("competitive")
    var competative: String = ""
    @SerializedName("quick")
    var quick: String = ""
}
