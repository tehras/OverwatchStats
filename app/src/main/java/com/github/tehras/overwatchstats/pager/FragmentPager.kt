package com.github.tehras.overwatchstats.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by tehras on 8/22/16.
 *
 * Fragment Manager
 */
class FragmentPager(fm: FragmentManager?, val fragmentCount: Int, val fragmentCallback: FragmentCallback) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentCallback.getItem(position)
    }

    override fun getCount(): Int {
        return fragmentCount
    }


    interface FragmentCallback {
        fun getItem(position: Int): Fragment
    }
}
