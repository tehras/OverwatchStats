package com.github.tehras.overwatchstats.models.heroes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import java.io.Serializable

/**
 * Created by tehras on 8/22/16.
 */
open class HeroGeneralStats : Serializable, RealmObject() {
    @SerializedName("games_won")
    @Expose
    var gamesWon: Double = 0.toDouble()
    @SerializedName("medals_gold")
    @Expose
    var medalsGold: Double = 0.toDouble()
    @SerializedName("eliminations")
    @Expose
    var eliminations: Double = 0.toDouble()
    @SerializedName("damage_done")
    @Expose
    var damageDone: Double = 0.toDouble()
    @SerializedName("multikill_best")
    @Expose
    var multikillBest: Double = 0.toDouble()
    @SerializedName("medals_bronze")
    @Expose
    var medalsBronze: Double = 0.toDouble()
    @SerializedName("eliminations_most_in_life")
    @Expose
    var eliminationsMostInLife: Double = 0.toDouble()
    @SerializedName("medals")
    @Expose
    var medals: Double = 0.toDouble()
    @SerializedName("games_played")
    @Expose
    var gamesPlayed: Double = 0.toDouble()
    @SerializedName("eliminations_per_life")
    @Expose
    var eliminationsPerLife: Double = 0.toDouble()
    @SerializedName("objective_time")
    @Expose
    var objectiveTime: String = "00:00"
    @SerializedName("time_spent_on_fire")
    @Expose
    var timeSpentOnFire: String = "00:00"
    @SerializedName("final_blows_most_in_game")
    @Expose
    var finalBlowsMostInGame: Double = 0.toDouble()
    @SerializedName("medals_silver")
    @Expose
    var medalsSilver: Double = 0.toDouble()
    @SerializedName("solo_kills")
    @Expose
    var soloKills: Double = 0.toDouble()
    @SerializedName("time_played")
    @Expose
    var timePlayed: String = "0 hours"
    @SerializedName("teleporter_pads_destroyed")
    @Expose
    var teleporterPadsDestroyed: Double = 0.toDouble()
    @SerializedName("eliminations_most_in_game")
    @Expose
    var eliminationsMostInGame: Double = 0.toDouble()
    @SerializedName("environmental_kills")
    @Expose
    var environmentalKills: Double = 0.toDouble()
    @SerializedName("environmental_deaths")
    @Expose
    var environmentalDeaths: Double = 0.toDouble()
    @SerializedName("damage_done_most_in_life")
    @Expose
    var damageDoneMostInLife: Double = 0.toDouble()
    @SerializedName("objective_time_most_in_game")
    @Expose
    var objectiveTimeMostInGame: String = "00:00"
    @SerializedName("cards")
    @Expose
    var cards: Double = 0.toDouble()
    @SerializedName("objective_kills_most_in_game")
    @Expose
    var objectiveKillsMostInGame: Double = 0.toDouble()
    @SerializedName("multikills")
    @Expose
    var multikills: Double = 0.toDouble()
    @SerializedName("win_percentage")
    @Expose
    var winPercentage: String = "0%"
    @SerializedName("final_blows")
    @Expose
    var finalBlows: Double = 0.toDouble()
    @SerializedName("solo_kills_most_in_game")
    @Expose
    var soloKillsMostInGame: Double = 0.toDouble()
    @SerializedName("damage_done_most_in_game")
    @Expose
    var damageDoneMostInGame: Double = 0.toDouble()
    @SerializedName("deaths")
    @Expose
    var deaths: Double = 0.toDouble()
    @SerializedName("objective_kills")
    @Expose
    var objectiveKills: Double = 0.toDouble()
}