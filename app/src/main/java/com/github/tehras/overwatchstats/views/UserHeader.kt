package com.github.tehras.overwatchstats.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.github.tehras.overwatchstats.R

/**
 * Created by tehras on 8/20/16.
 */
class UserHeader(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    init {
        View.inflate(context, R.layout.view_user_layout, this)
    }
}