package com.github.tehras.overwatchstats.models

import com.github.tehras.overwatchstats.models.champs.ConfigChamps
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.Ignore

/**
 * Created by tehras on 8/18/16.
 */
open class Champion(var name: String) : RealmObject() {
    constructor() : this("")

    @SerializedName("Eliminations")
    var eliminations: String = ""
    @SerializedName("FinalBlows")
    var finalBlows: Int = 0
    @SerializedName("SoloKills")
    var soloKills: Int = 0
    @SerializedName("DamageDone")
    var damageDone: String = ""
    @SerializedName("ObjectiveKills")
    var objectiveKills: Int = 0
    @SerializedName("MultiKills")
    var multiKills: Int = 0
    @SerializedName("CriticalHits")
    var criticalHits: String = ""
    @SerializedName("CriticalHitAccuracy")
    var criticalHitAccuracy: String = ""
    @SerializedName("EliminationsperLife")
    var eliminationsPerLife: Double = 0.toDouble()
    @SerializedName("WeaponAccuracy")
    var weaponAccuracy: String = ""
    @SerializedName("HealingDone")
    var healingDone: String = ""
    @SerializedName("SelfHealing")
    var selfHealing: String = ""
    @SerializedName("Eliminations-MostinLife")
    var mostElimsInLife: Int = 0
    @SerializedName("DamageDone-MostinLife")
    var mostDamageDoneInLife: String = ""
    @SerializedName("HealingDone-MostinLife")
    var mostHealingDoneInLife: String = ""
    @SerializedName("DamageDone-MostinGame")
    var mostDamageDoneInGame: String = ""
    @SerializedName("HealingDone-MostinGame")
    var mostHealingDoneInGame: String = ""
    @SerializedName("Eliminations-MostinGame")
    var mostEliminationsInGame: Int = 0
    @SerializedName("FinalBlows-MostinGame")
    var mostInGameFinalBlows: Int = 0
    @SerializedName("ObjectiveKills-MostinGame")
    var mostInGameObjectiveKils: Int = 0
    @SerializedName("ObjectiveTime-MostinGame")
    var mostInGameObjectiveTime: String = ""
    @SerializedName("SoloKills-MostinGame")
    var mostInGameSoloKills: Int = 0
    @SerializedName("CriticalHits-MostinGame")
    var mostInGameCriticalHits: String = ""
    @SerializedName("SelfHealing-MostinGame")
    var mostInGameSelfHealing: String = ""
    @SerializedName("Deaths-Average")
    var averageDeaths: Double = 0.toDouble()
    @SerializedName("SoloKills-Average")
    var averageSoloKills: Double = 0.toDouble()
    @SerializedName("ObjectiveTime-Average")
    var objectiveTimeAverage: String = "00:00"
    @SerializedName("ObjectiveKills-Average")
    var objectiveKillsAverage: Double = 0.toDouble()
    @SerializedName("HealingDone-Average")
    var healingDoneAverage: String = 0.toString()
    @SerializedName("FinalBlows-Average")
    var finalBlowsAverage: String = 0.toString()
    @SerializedName("Eliminations-Average")
    var eliminationsAverage: String = 0.toString()
    @SerializedName("DamageDone-Average")
    var damageDoneAverage: String = 0.toString()
    @SerializedName("Deaths")
    var deaths: Int = 0
    @SerializedName("Medals-Bronze")
    var bronzeMedals: Int = 0
    @SerializedName("Medals-Silver")
    var silverMedals: Int = 0
    @SerializedName("Medals-Gold")
    var goldMedals: Int = 0
    @SerializedName("Medals")
    var medals: Int = 0
    @SerializedName("TimePlayed")
    var timePlayed: String = "0hours"
    @SerializedName("GamesPlayed")
    var gamesPlayed: Int = 0
    @SerializedName("Cards")
    var cards: Int = 0
    @SerializedName("GamesWon")
    var gamesWon: Int = 0
    @SerializedName("ObjectiveTime")
    var objectiveTime: String = 0.toString()
    @SerializedName("TimeSpentonFire")
    var timeSpentOnFire: String = "00:00:00"
    @SerializedName("WinPercentage")
    var winPercentage: String = "00:00:00"
    @SerializedName("Multikill-Best")
    var multiKillBest: String = 0.toString()

    var lastUpdated: Long? = 0.toLong()

    @Ignore
    var configType: ConfigChamps.Type? = ConfigChamps.Type.ATTACK

    fun getConfirmedType(): ConfigChamps.Type {
        configChamps?.forEach {
            if (it.name.equals(name, true))
                return it.type
        }

        return ConfigChamps.Type.ATTACK
    }

}