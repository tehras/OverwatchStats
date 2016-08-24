package com.github.tehras.overwatchstats.views

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.github.tehras.overwatchstats.R

/**
 * Created by tehras on 8/20/16.
 *
 * Data With Hint
 */
class DataWithHint(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)


    private val SMALL = 0
    private val MEDIUM = 1
    private val LARGE = 2

    private val LIGHT = 0
    private val DARK = 1

    lateinit var subtitleVal: TextView
    lateinit var value: TextView

    init {
        var subtitle = "Subtitle"
        var subtitleColor = R.color.colorAccent
        var size = MEDIUM
        var textColor = LIGHT

        if (attrs != null) {
            val ta = context?.obtainStyledAttributes(attrs, R.styleable.DataWithHint, defStyleAttr, 0)
            subtitle = ta?.getString(R.styleable.DataWithHint_subtitle).toString()
            subtitleColor = ta?.getColor(R.styleable.DataWithHint_subtitleTextColor, R.color.colorAccent) ?: R.color.colorAccent
            size = ta?.getInt(R.styleable.DataWithHint_size, MEDIUM) ?: MEDIUM
            textColor = ta?.getInt(R.styleable.DataWithHint_textColorStyle, LIGHT) ?: LIGHT

            ta?.recycle()
        }

        View.inflate(context, R.layout.view_data_with_hint, this)

        subtitleVal = findViewById(R.id.view_data_subtitle) as TextView
        value = findViewById(R.id.view_data_value) as TextView

        subtitleVal.text = subtitle
        subtitleVal.setTextColor(subtitleColor)

        when (size) {
            SMALL -> setTextSizes(11, 14)
            MEDIUM -> setTextSizes(13, 17)
            LARGE -> setTextSizes(15, 20)
        }

        when (textColor) {
            LIGHT -> {
                value.setTextColor(context!!.resources.getColor(R.color.light_text_color, null))
            }
            else -> {
                value.setTextColor(context!!.resources.getColor(R.color.dark_text_color, null))
            }
        }
    }

    private fun setTextSizes(i: Int, i1: Int) {
        subtitleVal.setTextSize(TypedValue.COMPLEX_UNIT_SP, i.toFloat())
        value.setTextSize(TypedValue.COMPLEX_UNIT_SP, i1.toFloat())
    }
}