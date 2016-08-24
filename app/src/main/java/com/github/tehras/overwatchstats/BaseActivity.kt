package com.github.tehras.overwatchstats

import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.tehras.overwatchstats.views.BottomToolbarLayout

/**
 * Created by tehras on 8/22/16.
 */
open class BaseActivity : AppCompatActivity() {

    private var toolbar: BottomToolbarLayout? = null
    private val TAG = "BaseActivity"

    fun setBottomToolbar(toolbar: BottomToolbarLayout?) {
        Log.d(TAG, "toolbar $toolbar")
        this.toolbar = toolbar
    }

    fun twoItemToolbar(viewPager: ViewPager?, fabIcon: Int, label1: String, label1Icon: Int, label2: String, label2Icon: Int, onClickListener: BottomToolbarLayout.OnClickListener): BottomToolbarLayout? {
        return toolbar?.setLabel1(label1, label1Icon)
                ?.setLabel2(label2, label2Icon)
                ?.setViewPager(viewPager)
                ?.setOnToolbarListener(clickListener = onClickListener)
                ?.setFabIcon(fabIcon)
                ?.showLabels(2)?.show(false)
    }

}
