package com.github.tehras.overwatchstats.exts

import com.github.tehras.overwatchstats.models.User

/**
 * Created by tehras on 8/28/16.
 */
fun User.level(): Int {
    return (this.profile?.data?.star?.toSafeInt() ?: 0).times(this.profile?.data?.level ?: 0)
}

fun String.toSafeInt(): Int {
    try {
        return this.toInt()
    } catch (e: NumberFormatException) {
        return 0
    }
}

fun User.winPercentage(): Double {
    return wins().toDouble().times(100.toDouble()).div((losses().toDouble().plus(wins().toDouble())))
}

fun User.wins(): Int {
    return quickPlayWins() + competativeWins()
}

fun User.losses(): Int {
    return quickPlayLosses() + competativeLosses()
}

fun User.competativeLosses(): Int {
    return this.profile?.data?.games?.competative?.lost?.toSafeInt() ?: 0
}

fun User.quickPlayLosses(): Int {
    return this.profile?.data?.games?.quick?.lost?.toSafeInt() ?: 0
}

fun User.quickPlayWins(): Int {
    return this.profile?.data?.games?.quick?.wins?.toSafeInt() ?: 0
}

fun User.competativeWins(): Int {
    return this.profile?.data?.games?.competative?.wins?.toSafeInt() ?: 0
}
