package com.github.tehras.overwatchstats.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.github.tehras.overwatchstats.R

/**
 * Created by tehras on 8/29/16.
 *
 * Data With Bar View
 */
@Suppress("UNUSED")
class DataWithBarView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    private lateinit var bottomLabelTv: TextView
    private lateinit var rightLabelTv: TextView
    private lateinit var valueLabelTv: TextView
    private lateinit var fillerBar: FillerBar

    init {
        var bottomLabel = ""
        var rightLabel = ""

        if (attrs != null) {
            val ta = context?.obtainStyledAttributes(attrs, R.styleable.DataWithBarView, defStyleAttr, 0)
            bottomLabel = ta?.getString(R.styleable.DataWithBarView_fieldLabel).toString()
            rightLabel = ta?.getString(R.styleable.DataWithBarView_rightLabel).toString()

            ta?.recycle()
        }

        View.inflate(context, R.layout.view_single_data_layout, this)

        valueLabelTv = findViewById(R.id.single_data_value) as TextView
        rightLabelTv = findViewById(R.id.single_data_after_value) as TextView

        bottomLabelTv = findViewById(R.id.single_data_label) as TextView

        fillerBar = findViewById(R.id.single_data_bar) as FillerBar

        bottomLabelTv.text = bottomLabel
        rightLabelTv.text = rightLabel
    }

    fun setRightLabel(s: CharSequence): DataWithBarView {
        rightLabelTv.text = s

        return this
    }

    fun setBottomLabel(s: CharSequence): DataWithBarView {
        bottomLabelTv.text = s

        return this
    }

    fun setValueText(s: CharSequence): DataWithBarView {
        valueLabelTv.text = s

        return this
    }

    fun setBarWidth(p: Int): DataWithBarView {
        fillerBar.setWidthPercentage(p)

        return this
    }

    fun setBarColor(c: Int): DataWithBarView {
        fillerBar.setBarColor(c)

        return this
    }
}