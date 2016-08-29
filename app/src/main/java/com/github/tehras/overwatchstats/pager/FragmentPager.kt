package com.github.tehras.overwatchstats.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log

/**
 * Created by tehras on 8/22/16.
 *
 * Fragment Manager
 */
class FragmentPager(fm: FragmentManager?, val fragmentCount: Int, val fragmentCallback: FragmentCallback) : FragmentPagerAdapter(fm) {

    val TAG = "FragmentPager"

    override fun getItem(position: Int): Fragment {
        Log.d(TAG, "get item ${position}")
        return fragmentCallback.getItem(position)
    }

    override fun getCount(): Int {
        return fragmentCount
    }


    interface FragmentCallback {
        fun getItem(position: Int): Fragment
    }
}
