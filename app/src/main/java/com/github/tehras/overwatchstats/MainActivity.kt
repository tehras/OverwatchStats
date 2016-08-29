package com.github.tehras.overwatchstats

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.github.tehras.overwatchstats.exts.*
import com.github.tehras.overwatchstats.fragments.BaseFragment
import com.github.tehras.overwatchstats.fragments.home.HomeFragment
import com.github.tehras.overwatchstats.networking.client
import com.github.tehras.overwatchstats.views.BottomToolbarLayout
import com.github.ybq.android.spinkit.SpinKitView
import com.mancj.materialsearchbar.MaterialSearchBar

class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"
    private var mFragmentContainer: View? = null
    var mSearchView: MaterialSearchBar? = null
    var mSearchParent: LinearLayout? = null

    var mLoadingLayout: FrameLayout? = null
    var mSpinner: SpinKitView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        mFragmentContainer = findViewById(R.id.fragment_container)
        mSearchView = findViewById(R.id.search_bar_layout) as MaterialSearchBar
        mSearchParent = findViewById(R.id.search_bar_parent) as LinearLayout
        mSearchView?.setTextHintColor(R.color.transparentBlack)

        mLoadingLayout = findViewById(R.id.loading_layout) as FrameLayout
        mSpinner = findViewById(R.id.loading_spinner) as SpinKitView
        setBottomToolbar(findViewById(R.id.bottom_toolbar) as BottomToolbarLayout)

        //first time you load
        if (savedInstanceState == null) {
            startFragment(HomeFragment.create(), true)

            //lets get some Firebase stuff
            getInstance()?.fetchConfigs()?.initializeChamps()
        }
    }

    override fun onBackPressed() {
        if (mSearchParent?.visibility == View.VISIBLE) {
            hideSearchBar(mSearchView?.getCenterX() ?: 0, mSearchView?.getCenterY() ?: 0)
        } else if (mLoadingLayout?.visibility == View.VISIBLE) {
            hideLoading()
            client?.dispatcher()?.cancelAll()
        } else if (supportFragmentManager.backStackEntryCount > 0) {
            Log.d(TAG, "supportFragmentManager.backStackEntryCount ${supportFragmentManager.backStackEntryCount}")
            Log.d(TAG, "supportFragmentManager.fragments ${supportFragmentManager.fragments}")

            if (supportFragmentManager.backStackEntryCount == 1) {
                finish()
                return
            }

            if (supportFragmentManager.fragments != null) {
                if (supportFragmentManager.fragments.last() is BaseFragment && (supportFragmentManager.fragments.last() as BaseFragment).onBackPressed()) {
                    return
                } else
                    supportFragmentManager.popBackStackImmediate()
            }
        } else {
            Log.d(TAG, "activity finish")
            this.finish()
        }
    }
}
