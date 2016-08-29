package com.github.tehras.overwatchstats.fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.fragments.home.HomeFragment
import com.github.tehras.overwatchstats.views.BottomToolbarButton
import com.github.tehras.overwatchstats.views.BottomToolbarLayout

/**
 * Created by tehras on 8/22/16.
 *
 * View Pager Adapter
 */
open class ViewPagerFragment : BaseFragment() {

    private val TAG: String = "ViewPagerFragment"

    var viewPager: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_view_pager_layout, container, false)

        viewPager = v.findViewById(R.id.fragment_view_pager) as ViewPager

        return v
    }
}
