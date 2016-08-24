package com.github.tehras.overwatchstats.models.general_stats

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by tehras on 8/20/16.
 */
class AverageStats : Serializable {
    @SerializedName("damage_done_avg")
    val damageDoneAvg: Double = 0.toDouble()
    @SerializedName("deaths_avg")
    val deathsAvg: Double = 0.toDouble()
    @SerializedName("defensive_assists_avg")
    val defensiveAssistsAvg: Double = 0.toDouble()
    @SerializedName("eliminations_avg")
    val eliminationsAvg: Double = 0.toDouble()
    @SerializedName("final_blows_avg")
    val finalBlowsAvg: Double = 0.toDouble()
    @SerializedName("healing_done_avg")
    val healingDoneAvg: Double = 0.toDouble()
    @SerializedName("melee_final_blows_avg")
    val meleeFinalBlowsAvg: Double = 0.toDouble()
    @SerializedName("objective_kills_avg")
    val objectiveKillsAvg: Double = 0.toDouble()
    @SerializedName("objective_time_avg")
    val objectiveTimeAvg: Double = 0.toDouble()
    @SerializedName("offensive_assists_avg")
    val offensiveAssistsAvg: Double = 0.toDouble()
    @SerializedName("solo_kills_avg")
    val soloKillsAvg: Double = 0.toDouble()
    @SerializedName("time_spent_on_fire_avg")
    val timeSpentOnFireAvg: Double = 0.toDouble()
}
