package com.github.tehras.overwatchstats.views

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.github.tehras.overwatchstats.R

/**
 * Created by tehras on 8/22/16.
 *
 * This is an alternative to the toolbar
 */
@Suppress("UNUSED")
class BottomToolbarLayout(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr), ViewPager.OnPageChangeListener {
    private var scrollState = 0
    private var pagerPos = 0

    override fun onPageScrollStateChanged(state: Int) {
        Log.d(TAG, "onPageScrollStateChanged - state - $state")
        scrollState = state
        if (state == 0) {
            when (pagerPos) {
//                0 -> slidingBar.translationX = 0.toFloat()
//                1 -> slidingBar.translationX = slidingBar.measuredWidth.toFloat()
            }
        }

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    private val TAG = "BottomToolbarLayout"

    override fun onPageSelected(position: Int) {
        Log.d(TAG, "onPageSelected $position")

        pagerPos = position
        itemSelected = pagerPos + 1
        when (position) {
            0 -> slidingBar.alignAlongButton(b = label1)
            else -> slidingBar.alignAlongButton(b = label2)
//            0 -> slidingBar.translationX = 0.toFloat()
//            1 -> slidingBar.translationX = slidingBar.measuredWidth.toFloat()
        }
    }

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    private var homeButton: BottomToolbarButton
    private var settingsButton: BottomToolbarButton
    private var label2: BottomToolbarButton
    private var label1: BottomToolbarButton
    private var floatingButton: FloatingActionButton
    private var itemSelected: Int = 1

    private var viewPager: ViewPager? = null

    fun setViewPager(viewPager: ViewPager?): BottomToolbarLayout {
        this.viewPager = viewPager
        this.viewPager?.addOnPageChangeListener(this)
        this.viewPager?.setCurrentItem(0, true)

        label1.setOnClickListener() {
            Log.d(TAG, "label1 Clicked")
            viewPager?.setCurrentItem(0, true)
        }
        label2.setOnClickListener() { viewPager?.setCurrentItem(1, true) }

        return this
    }

    fun setLabel1(text: CharSequence, icon: Int): BottomToolbarLayout {
        label1.setText(text)
        if (icon != 0) label1.setIcon(icon)

        return this
    }

    fun setFabIcon(icon: Int): BottomToolbarLayout {
        floatingButton.setImageResource(icon)

        return this
    }

    fun showLabels(labels: Int): BottomToolbarLayout {
        when (labels) {
            0 -> {
                label1.visibility = View.INVISIBLE
                label2.visibility = View.INVISIBLE
            }
            1 -> {
                label1.visibility = View.VISIBLE
                label2.visibility = View.GONE
            }
            2 -> {
                label1.visibility = View.VISIBLE
                label2.visibility = View.VISIBLE
            }
        }

        return this
    }

    fun setLabel2(text: CharSequence, icon: Int): BottomToolbarLayout {
        label2.setText(text)
        if (icon != 0) label2.setIcon(icon)

        return this
    }

    fun setOnToolbarListener(clickListener: OnClickListener): BottomToolbarLayout {
        homeButton.setOnClickListener { clickListener.homePressed(homeButton) }
        settingsButton.setOnClickListener { clickListener.settingsPressed(settingsButton) }
        floatingButton.setOnClickListener { clickListener.fabPressed(floatingButton) }

        return this
    }

    fun showHome(show: Boolean): BottomToolbarLayout {
        if (show) homeButton.visibility = View.VISIBLE
        else homeButton.visibility = View.GONE

        return this
    }

    fun showSettings(show: Boolean): BottomToolbarLayout {
        if (show) settingsButton.visibility = View.VISIBLE
        else settingsButton.visibility = View.GONE

        return this
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        if (itemSelected == 1) {
            slidingBar.alignAlongButton(label1)
        } else {
            slidingBar.alignAlongButton(label2)
        }
    }

    private fun LinearLayout.alignAlongButton(b: BottomToolbarButton) {
        val params = this.layoutParams
        params.width = b.width

        this.left = b.left
        this.right = b.right

        this.translationX = 0.toFloat()
    }

    private var slidingBar: LinearLayout

    init {
        var icon = 0
        var label1Icon = 0
        var label2Icon = 0
        var label1Text = ""
        var label2Text = ""
        var showHome = true
        var showSettings = true

        if (attrs != null) {
            val ta = context?.obtainStyledAttributes(attrs, R.styleable.BottomToolbarLayout, defStyleAttr, 0)
            icon = ta?.getResourceId(R.styleable.BottomToolbarLayout_fabIcon, 0)!!
            label1Icon = ta?.getResourceId(R.styleable.BottomToolbarLayout_fabIcon, 0)!!
            label2Icon = ta?.getResourceId(R.styleable.BottomToolbarLayout_fabIcon, 0)!!

            label1Text = ta?.getString(R.styleable.BottomToolbarLayout_label1Text) ?: ""
            label2Text = ta?.getString(R.styleable.BottomToolbarLayout_label2Text) ?: ""

            showHome = ta?.getBoolean(R.styleable.BottomToolbarLayout_showHome, showHome)!!
            showSettings = ta?.getBoolean(R.styleable.BottomToolbarLayout_showSettings, showHome)!!

            ta?.recycle()
        }

        View.inflate(context, R.layout.view_bottom_toolbar_layout, this)

        homeButton = findViewById(R.id.bottom_home_button) as BottomToolbarButton
        settingsButton = findViewById(R.id.bottom_toolbar_settings) as BottomToolbarButton
        label1 = findViewById(R.id.bottom_toolbar_button_1) as BottomToolbarButton
        label2 = findViewById(R.id.bottom_toolbar_button_2) as BottomToolbarButton
        floatingButton = findViewById(R.id.bottom_toolbar_fab) as FloatingActionButton

        slidingBar = findViewById(R.id.bottom_toolbar_sliding_bar) as LinearLayout

        if (!showHome) homeButton.visibility = View.GONE
        if (!showSettings) settingsButton.visibility = View.GONE

        label1.setText(text = label1Text)
        label2.setText(text = label2Text)

        if (label1Icon != 0) label1.setIcon(label1Icon)
        if (label2Icon != 0) label2.setIcon(label2Icon)

        if (icon != 0) floatingButton.setImageResource(icon)
    }

    interface OnClickListener {
        fun fabPressed(fab: FloatingActionButton)
        fun settingsPressed(settingsButton: BottomToolbarButton)
        fun homePressed(homeButton: BottomToolbarButton)
    }

    fun hide(): BottomToolbarLayout {
        return hide(true)
    }

    fun hide(animate: Boolean): BottomToolbarLayout {
        if (animate) this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_slide_out_bottom))
        this.visibility = View.GONE

        return this
    }

    fun show(): BottomToolbarLayout {
        return show(true)
    }

    fun show(animate: Boolean): BottomToolbarLayout {
        if (animate) this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_slide_in_bottom))
        this.visibility = View.VISIBLE

        return this
    }
}
