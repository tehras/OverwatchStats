package com.github.tehras.overwatchstats.models.general_stats

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import java.io.Serializable

/**
 * Created by tehras on 8/20/16.
 *
 * GameStats
 */
@Suppress("UNUSED")
open class GameStats : Serializable, RealmObject() {
    @SerializedName("cards")
    @Expose
    var cards: Int? = 0
    @SerializedName("damage_done")
    @Expose
    var damageDone: Int? = 0
    @SerializedName("damage_done_most_in_game")
    @Expose
    var damageDoneMostInGame: Int? = 0
    @SerializedName("deaths")
    @Expose
    var deaths: Int? = 0
    @SerializedName("defensive_assists")
    @Expose
    var defensiveAssists: Int? = 0
    @SerializedName("defensive_assists_most_in_game")
    @Expose
    var defensiveAssistsMostInGame: Int? = 0
    @SerializedName("eliminations")
    @Expose
    var eliminations: Int? = 0
    @SerializedName("eliminations_most_in_game")
    @Expose
    var eliminationsMostInGame: Int? = 0
    @SerializedName("environmental_deaths")
    @Expose
    var environmentalDeaths: Int? = 0
    @SerializedName("environmental_kills")
    @Expose
    var environmentalKills: Int? = 0
    @SerializedName("final_blows")
    @Expose
    var finalBlows: Int? = 0
    @SerializedName("final_blows_most_in_game")
    @Expose
    var finalBlowsMostInGame: Int? = 0
    @SerializedName("games_played")
    @Expose
    var gamesPlayed: Int? = 0
    @SerializedName("games_won")
    @Expose
    var gamesWon: Int? = 0
    @SerializedName("healing_done")
    @Expose
    var healingDone: Int? = 0
    @SerializedName("healing_done_most_in_game")
    @Expose
    var healingDoneMostInGame: Int? = 0
    @SerializedName("kpd")
    @Expose
    var kpd: Double? = 0.toDouble()
    @SerializedName("medals")
    @Expose
    var medals: Int? = 0
    @SerializedName("medals_bronze")
    @Expose
    var medalsBronze: Int? = 0
    @SerializedName("medals_gold")
    @Expose
    var medalsGold: Int? = 0
    @SerializedName("medals_silver")
    @Expose
    var medalsSilver: Int? = 0
    @SerializedName("melee_final_blows")
    @Expose
    var meleeFinalBlows: Int? = 0
    @SerializedName("melee_final_blows_most_in_game")
    @Expose
    var meleeFinalBlowsMostInGame: Int? = 0
    @SerializedName("multikill_best")
    @Expose
    var multikillBest: Int? = 0
    @SerializedName("multikills")
    @Expose
    var multikills: Int? = 0
    @SerializedName("objective_kills")
    @Expose
    var objectiveKills: Int? = 0
    @SerializedName("objective_kills_most_in_game")
    @Expose
    var objectiveKillsMostInGame: Int? = 0
    @SerializedName("objective_time")
    @Expose
    var objectiveTime: Double? = 0.toDouble()
    @SerializedName("objective_time_most_in_game")
    @Expose
    var objectiveTimeMostInGame: Double? = 0.toDouble()
    @SerializedName("offensive_assists")
    @Expose
    var offensiveAssists: Int? = 0
    @SerializedName("offensive_assists_most_in_game")
    @Expose
    var offensiveAssistsMostInGame: Int? = 0
    @SerializedName("recon_assists")
    @Expose
    var reconAssists: Int? = 0
    @SerializedName("solo_kills")
    @Expose
    var soloKills: Int? = 0
    @SerializedName("solo_kills_most_in_game")
    @Expose
    var soloKillsMostInGame: Int? = 0
    @SerializedName("time_played")
    @Expose
    var timePlayed: Int? = 0
    @SerializedName("time_spent_on_fire")
    @Expose
    var timeSpentOnFire: Double? = 0.toDouble()
    @SerializedName("time_spent_on_fire_most_in_game")
    @Expose
    var timeSpentOnFireMostInGame: Double? = 0.toDouble()
}