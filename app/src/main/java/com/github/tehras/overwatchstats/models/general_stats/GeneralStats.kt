package com.github.tehras.overwatchstats.models.general_stats

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import java.io.Serializable

/**
 * Created by tehras on 8/20/16.
 */
open class GeneralStats : Serializable, RealmObject() {

    @SerializedName("average_stats")
    var averageStats: AverageStats? = null
    @SerializedName("battletag")
    var battletag: String? = ""
    @SerializedName("game_stats")
    var gameStats: GameStats? = null
    @SerializedName("overall_stats")
    var overallStats: OverallStats? = null
    @SerializedName("region")
    var region: String? = "us"
}
