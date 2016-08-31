package com.github.tehras.overwatchstats.exts

import android.content.Context
import android.widget.ImageView
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.models.OWAPIUser
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

fun OWAPIUser.mostElims(): Int {
    var elims = 0

    this.heroes?.heroes?.forEach {
        if (elims < (it.generalStats?.eliminations?.toInt() ?: 0))
            elims = it.generalStats?.eliminations?.toInt() ?: 0
    }

    return elims
}

fun OWAPIUser.highestAverageDmg(): Double {
    var highestAverageDmg = 0.toDouble()

    this.heroes?.heroes?.forEach {
        val h = it.generalStats?.damageDone?.div(it.generalStats?.gamesPlayed ?: 1.toDouble()) ?: 0.toDouble()
        if (h > highestAverageDmg) {
            highestAverageDmg = h
        }
    }

    return highestAverageDmg
}

fun OWAPIUser.highestAverageTotalDmg(): Double {
    var highestTotalDmg = 0.toDouble()

    this.heroes?.heroes?.forEach {
        val h = it.generalStats?.damageDone ?: 0.toDouble()
        if (h > highestTotalDmg)
            highestTotalDmg = h
    }

    return highestTotalDmg
}

fun OWAPIUser.highestMostDmg(): Double {
    var mostDmgInGame = 0.toDouble()

    this.heroes?.heroes?.forEach {
        val m = it.generalStats?.damageDoneMostInGame ?: 0.toDouble()
        if (m > mostDmgInGame)
            mostDmgInGame = m
    }

    return mostDmgInGame
}

fun Hero.averageObjTime(): String {
    return "${this.averageObjTimeD().format(0)}s"
}

fun Hero.averageObjTimeD(): Double {
    val split = this.generalStats?.objectiveTime?.split(":")

    var v = 0.toDouble()
    var l = split?.size ?: 0
    if (l > 0)
        split?.forEach {
            v += l * 60 * it.toDouble()
            l--
        }

    //convert back
    val t = v.div(this.played ?: 1.toDouble())

    return t.div(MINUTES)
}

fun Hero.objectiveKills(): Double {
    return this.generalStats?.objectiveKills?.div(this.generalStats?.gamesPlayed ?: 1.toDouble()) ?: 0.toDouble()
}

fun Hero.averageDeaths(): Double {
    return this.generalStats?.deaths?.div(this.generalStats?.gamesPlayed ?: 1.toDouble()) ?: 0.toDouble()
}

fun OWAPIUser.mostDeaths(): Double {
    var d = 0.toDouble()
    this.heroes?.heroes?.forEach {
        val i = it.averageDeaths()
        if (i > d)
            d = i
    }
    return d
}

fun OWAPIUser.highestObjectiveKills(): Double {
    var h = 0.toDouble()

    this.heroes?.heroes?.forEach {
        val ho = it.objectiveKills()
        if (ho > h)
            h = ho
    }
    return h
}

fun OWAPIUser.highestObjTime(): Double {
    var h = 0.toDouble()

    this.heroes?.heroes?.forEach {
        val ho = it.averageObjTimeD()
        if (ho > h)
            h = ho
    }

    return h
}

fun Hero.highestAverageDmg(): String {
    return this.averageDmgJustNum().formatThousands(1)
}

fun Hero.averageDmgJustNum(): Double {
    return ((this.generalStats?.damageDone ?: 0.toDouble()).div(this.generalStats?.gamesPlayed ?: 1.toDouble()))
}

fun OWAPIUser.highestElimsPerLife(): Double {
    var elims = 0.toDouble()

    this.heroes?.heroes?.forEach {
        val m = it.generalStats?.eliminationsPerLife ?: 0.toDouble()

        if (m > elims)
            elims = m
    }

    return elims
}

fun OWAPIUser.highestElimsInGame(): Int {
    var elims = 0

    this.heroes?.heroes?.forEach {
        if (elims < (it.generalStats?.eliminationsMostInGame?.toInt() ?: 0))
            elims = it.generalStats?.eliminationsMostInGame?.toInt() ?: 0
    }

    return elims
}

fun Hero.winPercentage(): Double {
    val won = generalStats?.gamesWon?.toDouble()
    var total = generalStats?.gamesPlayed?.toDouble()

    if (total == 0.toDouble())
        total = 1.toDouble()

    return won?.toDouble()?.div(total?.toDouble() ?: 1.toDouble()) ?: 0.toDouble()
}


fun Hero.loadImage(context: Context, image: ImageView) {
    image.setImageDrawable(context.resources.getDrawable(getDynamicImage(this, context), null))
}

private fun getDynamicImage(hero: Hero?, context: Context): Int {
    val id = context.resources?.getIdentifier(getDynamicName(hero), "drawable", context.packageName)?.toInt() ?: 0

    if (id.toInt() == 0)
        return R.drawable.ic_soldier76
    else
        return id
}

private fun getDynamicName(hero: Hero?): String? {
    return "ic_${hero?.name?.toLowerCase() ?: "bastion"}"
}