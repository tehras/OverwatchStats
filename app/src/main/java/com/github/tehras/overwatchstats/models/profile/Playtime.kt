package com.github.tehras.overwatchstats.models.profile

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by tehras on 8/18/16.
 *
 * Playtime
 */
class Playtime : Serializable {
    @SerializedName("competitive")
    var competative: String = ""
    @SerializedName("quick")
    var quick: String = ""
}
