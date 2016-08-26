package com.github.tehras.overwatchstats.exts

import io.realm.RealmList
import io.realm.RealmModel

/**
 * Created by tehras on 8/25/16.
 */
fun <T : RealmModel> RealmList<T>.getIndex(test: T, func: T.(T) -> Boolean): Int {
    this.forEachIndexed { i, t ->
        if (test.func(t))
            return i
    }

    return -1
}
