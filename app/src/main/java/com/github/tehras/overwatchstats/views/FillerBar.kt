package com.github.tehras.overwatchstats.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.github.tehras.overwatchstats.R

/**
 * Created by tehras on 8/29/16.
 *
 * Filler Bar
 */
class FillerBar(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    lateinit var barColor: Paint
    lateinit var outlinePaint: Paint
    var percentage: Int = 100

    init {
        //width
        var barColorStart: Int = R.color.attack
        percentage = 100

        if (attrs != null) {
            val ta = context?.obtainStyledAttributes(attrs, R.styleable.FillerBar, defStyleAttr, 0)
            barColorStart = ta?.getColor(R.styleable.FillerBar_android_color, barColorStart) ?: barColorStart
            percentage = ta?.getInt(R.styleable.FillerBar_barPercentage, 100) ?: percentage

            ta?.recycle()
        }

        barColor = Paint()
        barColor.style = Paint.Style.FILL
        barColor.color = barColorStart

        outlinePaint = Paint()
        outlinePaint.color = Color.BLACK
        outlinePaint.style = Paint.Style.STROKE
        outlinePaint.strokeWidth = 3.toFloat()

    }

    fun setBarColor(color: Int) {
        barColor.color = color
    }

    //0 to 100
    fun setWidthPercentage(percentage: Int) {
        this.percentage = percentage
    }

    val outlineRect = RectF()

    override fun onDraw(canvas: Canvas) {
        //lets start drawing!
        //outline first
        val radius = height / 2

        outlineRect.set(0.toFloat(), 0.toFloat(), width.toFloat(), height.toFloat())
        canvas.drawRoundRect(outlineRect, radius.toFloat(), radius.toFloat(), outlinePaint)

        val newR = width.times(percentage).div(100)

        outlineRect.set(0.toFloat(), 3.toFloat(), newR.toFloat(), (height - 3).toFloat())

        canvas.drawRoundRect(outlineRect, radius.toFloat(), radius.toFloat(), barColor)
    }
}