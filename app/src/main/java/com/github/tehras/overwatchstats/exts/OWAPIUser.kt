package com.github.tehras.overwatchstats.exts

import com.github.tehras.overwatchstats.models.heroes.Hero

/**
 * Created by tehras on 8/28/16.
 *
 * Helper
 */

fun Hero.winLosses(): String {
    val won = this.generalStats?.gamesWon?.toInt() ?: 0
    val losses = (this.generalStats?.gamesPlayed?.minus(this.generalStats?.gamesWon ?: 0.toDouble()))?.toInt()

    return "${won}W/${losses}L"
}

fun Hero.winPercentage(): Double {
    val won = generalStats?.gamesWon?.toDouble()
    var total = generalStats?.gamesPlayed?.toDouble()

    if (total == 0.toDouble())
        total = 1.toDouble()

    return won?.toDouble()?.div(total?.toDouble() ?: 1.toDouble()) ?: 0.toDouble()
}