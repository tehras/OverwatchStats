package com.github.tehras.overwatchstats.models.general_stats

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by tehras on 8/20/16.
 *
 * GameStats
 */
@Suppress("UNUSED")
class GameStats : Serializable {
    @SerializedName("cards")
    @Expose
    val cards: Int? = 0
    @SerializedName("damage_done")
    @Expose
    val damageDone: Int? = 0
    @SerializedName("damage_done_most_in_game")
    @Expose
    val damageDoneMostInGame: Int? = 0
    @SerializedName("deaths")
    @Expose
    val deaths: Int? = 0
    @SerializedName("defensive_assists")
    @Expose
    val defensiveAssists: Int? = 0
    @SerializedName("defensive_assists_most_in_game")
    @Expose
    val defensiveAssistsMostInGame: Int? = 0
    @SerializedName("eliminations")
    @Expose
    val eliminations: Int? = 0
    @SerializedName("eliminations_most_in_game")
    @Expose
    val eliminationsMostInGame: Int? = 0
    @SerializedName("environmental_deaths")
    @Expose
    val environmentalDeaths: Int? = 0
    @SerializedName("environmental_kills")
    @Expose
    val environmentalKills: Int? = 0
    @SerializedName("final_blows")
    @Expose
    val finalBlows: Int? = 0
    @SerializedName("final_blows_most_in_game")
    @Expose
    val finalBlowsMostInGame: Int? = 0
    @SerializedName("games_played")
    @Expose
    val gamesPlayed: Int? = 0
    @SerializedName("games_won")
    @Expose
    val gamesWon: Int? = 0
    @SerializedName("healing_done")
    @Expose
    val healingDone: Int? = 0
    @SerializedName("healing_done_most_in_game")
    @Expose
    val healingDoneMostInGame: Int? = 0
    @SerializedName("kpd")
    @Expose
    val kpd: Double? = 0.toDouble()
    @SerializedName("medals")
    @Expose
    val medals: Int? = 0
    @SerializedName("medals_bronze")
    @Expose
    val medalsBronze: Int? = 0
    @SerializedName("medals_gold")
    @Expose
    val medalsGold: Int? = 0
    @SerializedName("medals_silver")
    @Expose
    val medalsSilver: Int? = 0
    @SerializedName("melee_final_blows")
    @Expose
    val meleeFinalBlows: Int? = 0
    @SerializedName("melee_final_blows_most_in_game")
    @Expose
    val meleeFinalBlowsMostInGame: Int? = 0
    @SerializedName("multikill_best")
    @Expose
    val multikillBest: Int? = 0
    @SerializedName("multikills")
    @Expose
    val multikills: Int? = 0
    @SerializedName("objective_kills")
    @Expose
    val objectiveKills: Int? = 0
    @SerializedName("objective_kills_most_in_game")
    @Expose
    val objectiveKillsMostInGame: Int? = 0
    @SerializedName("objective_time")
    @Expose
    val objectiveTime: Double? = 0.toDouble()
    @SerializedName("objective_time_most_in_game")
    @Expose
    val objectiveTimeMostInGame: Double? = 0.toDouble()
    @SerializedName("offensive_assists")
    @Expose
    val offensiveAssists: Int? = 0
    @SerializedName("offensive_assists_most_in_game")
    @Expose
    val offensiveAssistsMostInGame: Int? = 0
    @SerializedName("recon_assists")
    @Expose
    val reconAssists: Int? = 0
    @SerializedName("solo_kills")
    @Expose
    val soloKills: Int? = 0
    @SerializedName("solo_kills_most_in_game")
    @Expose
    val soloKillsMostInGame: Int? = 0
    @SerializedName("time_played")
    @Expose
    val timePlayed: Int? = 0
    @SerializedName("time_spent_on_fire")
    @Expose
    val timeSpentOnFire: Double? = 0.toDouble()
    @SerializedName("time_spent_on_fire_most_in_game")
    @Expose
    val timeSpentOnFireMostInGame: Double? = 0.toDouble()
}