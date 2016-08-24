package com.github.tehras.overwatchstats.exts

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by tehras on 8/21/16.
 */

fun RecyclerView.initStandardLinearLayout(context: Context) {
    this.setHasFixedSize(true)
    this.layoutManager = LinearLayoutManager(context)
}
