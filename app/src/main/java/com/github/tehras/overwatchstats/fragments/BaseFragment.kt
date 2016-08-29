package com.github.tehras.overwatchstats.fragments

import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import com.github.tehras.overwatchstats.fragments.home.HomeFragment
import com.github.tehras.overwatchstats.networking.client
import com.github.tehras.overwatchstats.networking.runners
import com.github.tehras.overwatchstats.views.BottomToolbarButton
import com.github.tehras.overwatchstats.views.BottomToolbarLayout

/**
 * Created by tehras on 8/17/16.
 *
 * Just a general fragment
 */
open class BaseFragment : Fragment(), BottomToolbarLayout.OnClickListener {
    override fun settingsPressed(settingsButton: BottomToolbarButton) {

    }

    override fun homePressed(homeButton: BottomToolbarButton) {
        val fm = activity.supportFragmentManager
        for (i in 0..fm.backStackEntryCount - 1) {
            if (fm.fragments[i] !is HomeFragment)
                fm.popBackStack()
        }
    }

    override fun fabPressed(fab: FloatingActionButton) {
    }

    //common reusable components
    open fun onBackPressed(): Boolean {
        return false
    }

    private val TAG = "BaseFragment"

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        if (canCancelAllCalls()) {
            Log.d(TAG, "dispatcher - queuedCalls - ${client?.dispatcher()?.queuedCallsCount()} , runningCalls - ${client?.dispatcher()?.runningCallsCount()}")
            client?.dispatcher()?.cancelAll()//cancels current
            client?.dispatcher()?.queuedCalls()?.forEach {
                it.cancel()//cancels the rest
            }
            client?.dispatcher()?.runningCalls()?.forEach {
                it.cancel()//cancels the rest
            }

            if (runners != null)
                runners?.forEach { it.cancel(true) }
        }

        super.onDestroy()
    }

    open fun canCancelAllCalls(): Boolean {
        return true
    }

}

