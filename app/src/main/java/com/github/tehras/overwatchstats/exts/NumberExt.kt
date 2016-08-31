package com.github.tehras.overwatchstats.exts

import android.widget.TextView
import com.github.tehras.overwatchstats.R

/**
 * Created by tehras on 8/22/16.
 */
fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

fun TextView.setWinPercentage(d: Double) {
    this.text = "${(d * 100.toDouble()).format(1)}%"

    var color = R.color.poor
    if (d > 0.65.toDouble())
        color = R.color.great
    else if (d > 0.55.toDouble())
        color = R.color.good
    else if (d > 0.45.toDouble())
        color = R.color.average

    this.setTextColor(this.context.resources.getColor(color, null))
}

fun Double.formatThousands(digits: Int): String {
    if (this > 1000000.toDouble()) {
        return "${this.div(1000000.toDouble()).format(digits)}M"
    } else if (this > 1000.toDouble()) {
        return "${this.div(1000.toDouble()).format(digits)}K"
    } else
        return "${this.format(digits)}"
}

fun Double.formatThousands(): String = this.formatThousands(0)

fun Double.isZero(): Boolean {
    return this.compareTo(0.toDouble()) == 0
}
