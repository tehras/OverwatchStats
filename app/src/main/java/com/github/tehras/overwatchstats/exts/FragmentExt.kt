package com.github.tehras.overwatchstats.exts

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by tehras on 8/18/16.
 */

fun <T : Fragment> T.bundle(func: Bundle.() -> Unit): T {
    val args = Bundle()
    args.func()
    this.arguments = args

    return this
}
