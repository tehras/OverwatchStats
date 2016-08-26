package com.github.tehras.overwatchstats.models.general_stats

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import java.io.Serializable

/**
 * Created by tehras on 8/20/16.
 */
open class AverageStats : Serializable, RealmObject() {
    @SerializedName("damage_done_avg")
    var damageDoneAvg: Double = 0.toDouble()
    @SerializedName("deaths_avg")
    var deathsAvg: Double = 0.toDouble()
    @SerializedName("defensive_assists_avg")
    var defensiveAssistsAvg: Double = 0.toDouble()
    @SerializedName("eliminations_avg")
    var eliminationsAvg: Double = 0.toDouble()
    @SerializedName("final_blows_avg")
    var finalBlowsAvg: Double = 0.toDouble()
    @SerializedName("healing_done_avg")
    var healingDoneAvg: Double = 0.toDouble()
    @SerializedName("melee_final_blows_avg")
    var meleeFinalBlowsAvg: Double = 0.toDouble()
    @SerializedName("objective_kills_avg")
    var objectiveKillsAvg: Double = 0.toDouble()
    @SerializedName("objective_time_avg")
    var objectiveTimeAvg: Double = 0.toDouble()
    @SerializedName("offensive_assists_avg")
    var offensiveAssistsAvg: Double = 0.toDouble()
    @SerializedName("solo_kills_avg")
    var soloKillsAvg: Double = 0.toDouble()
    @SerializedName("time_spent_on_fire_avg")
    var timeSpentOnFireAvg: Double = 0.toDouble()
}
