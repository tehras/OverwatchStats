package com.github.tehras.overwatchstats.models.general_stats

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by tehras on 8/20/16.
 */
class OverallStats : Serializable {
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
    @SerializedName("comprank")
    @Expose
    var comprank: Int? = 0
    @SerializedName("games")
    @Expose
    var games: Int? = 0
    @SerializedName("level")
    @Expose
    var level: Int? = 0
    @SerializedName("losses")
    @Expose
    var losses: Int? = 0
    @SerializedName("prestige")
    @Expose
    var prestige: Int? = 0
    @SerializedName("win_rate")
    @Expose
    var winRate: Int? = 0
    @SerializedName("wins")
    @Expose
    var wins: Int? = 0
}