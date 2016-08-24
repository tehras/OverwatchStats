package com.github.tehras.overwatchstats.models.profile

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by tehras on 8/18/16.
 *
 * League
 */
class League : Serializable {
    @SerializedName("wins")
    var wins: String? = "0"
    @SerializedName("lost")
    var lost: String? = "0"
    @SerializedName("played")
    var played: String? = "0"
}
