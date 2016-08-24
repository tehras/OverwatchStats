package com.github.tehras.overwatchstats.models.profile

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by tehras on 8/18/16.
 *
 * Games
 */
class Games : Serializable {
    @SerializedName("quick")
    var quick: League? = null
    @SerializedName("competitive")
    var competative: League? = null

}