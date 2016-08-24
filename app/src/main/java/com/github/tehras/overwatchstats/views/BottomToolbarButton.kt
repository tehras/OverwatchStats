package com.github.tehras.overwatchstats.views

import android.content.Context
import android.support.annotation.IntegerRes
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.tehras.overwatchstats.R

/**
 * Created by tehras on 8/22/16.
 *
 * Bottom Toolbar Button
 */
class BottomToolbarButton(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    private lateinit var toolbarText: TextView
    private lateinit var toolbarIcon: ImageView

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    init {
        var icon = 0
        var text = ""
        var showText = true

        if (attrs != null) {
            val ta = context?.obtainStyledAttributes(attrs, R.styleable.BottomToolbarButton, defStyleAttr, 0)
            icon = ta?.getResourceId(R.styleable.BottomToolbarButton_android_src, 0)!!
            text = ta?.getString(R.styleable.BottomToolbarButton_android_text) ?: ""
            showText = ta?.getBoolean(R.styleable.BottomToolbarButton_showText, showText)!!

            ta?.recycle()
        }

        View.inflate(context, R.layout.view_bottom_toolbar_button, this)

        toolbarIcon = findViewById(R.id.bottom_toolbar_icon) as ImageView
        toolbarText = findViewById(R.id.bottom_toolbar_text) as TextView

        if (!showText) toolbarText.visibility = View.GONE
        if (icon != 0) toolbarIcon.setImageResource(icon)
        toolbarText.text = text
    }

    fun setText(text: CharSequence) {
        toolbarText.text = text
    }

    fun showText(showText: Boolean) {
        if (showText) toolbarText.visibility = View.VISIBLE
        else toolbarText.visibility = View.GONE
    }

    @IntegerRes
    fun setIcon(id: Int) {
        toolbarIcon.setImageResource(id)
    }
}
