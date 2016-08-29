package com.github.tehras.overwatchstats.exts

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.tehras.overwatchstats.BaseActivity
import com.github.tehras.overwatchstats.MainActivity
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.views.BottomToolbarLayout
import com.mancj.materialsearchbar.MaterialSearchBar

/**
 * Created by tehras on 8/17/16.
 *
 * Start Fragment
 */
fun AppCompatActivity.startFragment(fragment: Fragment, first: Boolean) {
    val tran = this.supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top)

    if (first)
        tran.replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
    else
        tran.replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)

    tran.addToBackStack(fragment.javaClass.simpleName)
            .commit()
}

fun Activity.showLoading() {
    if (this is MainActivity) {
        mLoadingLayout?.setOnTouchListener { view, motionEvent -> true }
        mLoadingLayout?.show(mLoadingLayout?.getCenterX() ?: 0, mLoadingLayout?.getCenterY() ?: 0)
    }
}

fun Activity.hideLoading() {
    if (this is MainActivity) {
        mLoadingLayout?.hide(mLoadingLayout?.getCenterX() ?: 0, mLoadingLayout?.getCenterY() ?: 0)

    }
}

fun Activity.showSearchBar(x: Int, y: Int, callback: SearchLayoutListener): MaterialSearchBar? {
    if (this is MainActivity) {
        val searchBar = this.mSearchView
        val searchLayout = this.mSearchParent

        if (searchLayout?.visibility != View.VISIBLE) {
            searchLayout?.setOnTouchListener { view, motionEvent ->
                hideSearchBar(x, y)
                searchLayout.setOnTouchListener(null)
                true
            }

            searchLayout?.show(x, y)

            searchBar?.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
                override fun onButtonClicked(p0: Int) {
                }

                override fun onSearchStateChanged(p0: Boolean) {
                    if (!p0)
                        searchLayout?.hide(x, y)
                }

                override fun onSearchConfirmed(p0: CharSequence?) {
                    searchLayout?.hide(x, y)

                    callback.onSearchEntered(p0)
                }
            })
        }
        return searchBar
    }
    return null
}

fun Activity.hideSearchBar() {
    if (this is MainActivity) {
        val searchBar = this.mSearchView
        val searchLayout = this.mSearchParent

        if (searchLayout?.visibility != View.GONE) {
            searchLayout?.hide()
            searchBar?.text = ""
        }
    }
}

fun Activity.hideSearchBar(x: Int, y: Int) {
    if (this is MainActivity) {
        val searchBar = this.mSearchView
        val searchLayout = this.mSearchParent

        if (searchLayout?.visibility != View.GONE) {
            searchLayout?.hide(x, y)
            searchBar?.text = ""
        }
    }
}

fun Activity.showTwoItemToolbar(viewPager: ViewPager?, fabIcon: Int, label1: String, label1Icon: Int, label2: String, label2Icon: Int, onClickListener: BottomToolbarLayout.OnClickListener): BottomToolbarLayout? {
    if (this is BaseActivity)
        return this.twoItemToolbar(viewPager, fabIcon, label1, label1Icon, label2, label2Icon, onClickListener)

    return null
}

interface SearchLayoutListener {
    fun onSearchEntered(text: CharSequence?)
}
