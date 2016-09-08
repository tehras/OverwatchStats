package com.github.tehras.overwatchstats.exts

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.tehras.overwatchstats.R

/**
 * Created by tehras on 8/21/16.
 */

fun RecyclerView.initStandardLinearLayout(context: Context) {
    this.setHasFixedSize(true)
    this.layoutManager = LinearLayoutManager(context)
    this.addItemDecoration(BottomOffsetDecoration(context.resources.getDimensionPixelSize(R.dimen.bottom_toolbar_height)))
}

internal class BottomOffsetDecoration(private val mBottomOffset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val dataSize = state.itemCount
        val position = parent.getChildPosition(view)
        if (dataSize > 0 && position == dataSize - 1) {
            outRect.set(0, 0, 0, mBottomOffset)
        } else {
            outRect.set(0, 0, 0, 0)
        }

    }
}