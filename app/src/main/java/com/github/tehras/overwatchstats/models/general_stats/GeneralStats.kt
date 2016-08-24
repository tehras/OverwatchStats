package com.github.tehras.overwatchstats.models.general_stats

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by tehras on 8/20/16.
 */
class GeneralStats : Serializable {

    @SerializedName("average_stats")
    val averageStats: AverageStats? = null
    @SerializedName("battletag")
    val battletag: String? = ""
    @SerializedName("game_stats")
    val gameStats: GameStats? = null
    @SerializedName("overall_stats")
    val overallStats: OverallStats? = null
    @SerializedName("region")
    val region: String? = "us"
}
