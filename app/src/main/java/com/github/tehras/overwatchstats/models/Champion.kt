package com.github.tehras.overwatchstats.models

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

/**
 * Created by tehras on 8/18/16.
 */
class Champion {
    constructor(name: String) {
        this.name = name
    }

    lateinit var name: String
    @SerializedName("Eliminations")
    var eliminations: String = ""
    @SerializedName("FinalBlows")
    val finalBlows: Int = 0
    @SerializedName("SoloKills")
    val soloKills: Int = 0
    @SerializedName("DamageDone")
    val damageDone: String = ""
    @SerializedName("ObjectiveKills")
    val objectiveKills: Int = 0
    @SerializedName("MultiKills")
    val multiKills: Int = 0
    @SerializedName("MeleeFinalBlows")
    val meleeFinalBlows: Int = 0
    @SerializedName("CriticalHits")
    val criticalHits: String = ""
    @SerializedName("CriticalHitAccuracy")
    val criticalHitAccuracy: String = ""
    @SerializedName("EliminationsperLife")
    val eliminationsPerLife: Double = 0.toDouble()
    @SerializedName("WeaponAccuracy")
    val weaponAccuracy: String = ""
    @SerializedName("HealingDone")
    val healingDone: String = ""
    @SerializedName("SelfHealing")
    val selfHealing: String = ""
    @SerializedName("Eliminations-MostinLife")
    val mostElimsInLife: Int = 0
    @SerializedName("DamageDone-MostinLife")
    val mostDamageDoneInLife: String = ""
    @SerializedName("HealingDone-MostinLife")
    val mostHealingDoneInLife: String = ""
    @SerializedName("DamageDone-MostinGame")
    val mostDamageDoneInGame: String = ""
    @SerializedName("HealingDone-MostinGame")
    val mostHealingDoneInGame: String = ""
    @SerializedName("Eliminations-MostinGame")
    val mostEliminationsInGame: Int = 0
    @SerializedName("FinalBlows-MostinGame")
    val mostInGameFinalBlows: Int = 0
    @SerializedName("ObjectiveKills-MostinGame")
    val mostInGameObjectiveKils: Int = 0
    @SerializedName("ObjectiveTime-MostinGame")
    val mostInGameObjectiveTime: String = ""
    @SerializedName("SoloKills-MostinGame")
    val mostInGameSoloKills: Int = 0
    @SerializedName("CriticalHits-MostinGame")
    val mostInGameCriticalHits: String = ""
    @SerializedName("SelfHealing-MostinGame")
    val mostInGameSelfHealing: String = ""
    @SerializedName("Deaths-Average")
    val averageDeaths: Double = 0.toDouble()
    @SerializedName("SoloKills-Average")
    val averageSoloKills: Double = 0.toDouble()
    @SerializedName("ObjectiveTime-Average")
    val objectiveTimeAverage: String = "00:00"
    @SerializedName("ObjectiveKills-Average")
    val objectiveKillsAverage: Double = 0.toDouble()
    @SerializedName("HealingDone-Average")
    val healingDoneAverage: String = 0.toString()
    @SerializedName("FinalBlows-Average")
    val finalBlowsAverage: String = 0.toString()
    @SerializedName("Eliminations-Average")
    val eliminationsAverage: String = 0.toString()
    @SerializedName("DamageDone-Average")
    val damageDoneAverage: String = 0.toString()
    @SerializedName("Deaths")
    val deaths: Int = 0
    @SerializedName("Medals-Bronze")
    val bronzeMedals: Int = 0
    @SerializedName("Medals-Silver")
    val silverMedals: Int = 0
    @SerializedName("Medals-Gold")
    val goldMedals: Int = 0
    @SerializedName("Medals")
    val medals: Int = 0
    @SerializedName("TimePlayed")
    val timePlayed: String = "0hours"
    @SerializedName("GamesPlayed")
    val gamesPlayed: Int = 0
    @SerializedName("Cards")
    val cards: Int = 0
    @SerializedName("GamesWon")
    val gamesWon: Int = 0
    @SerializedName("ObjectiveTime")
    val objectiveTime: String = 0.toString()
    @SerializedName("TimeSpentonFire")
    val timeSpentOnFire: String = "00:00:00"
    @SerializedName("WinPercentage")
    val winPercentage: String = "00:00:00"
    @SerializedName("Multikill-Best")
    val multiKillBest: String = 0.toString()

}