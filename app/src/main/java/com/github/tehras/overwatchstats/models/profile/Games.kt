package com.github.tehras.overwatchstats.models.profile

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by tehras on 8/18/16.
 *
 * Games
 */
open class Games : RealmObject() {
    @SerializedName("quick")
    var quick: League? = null
    @SerializedName("competitive")
    var competative: League? = null

}