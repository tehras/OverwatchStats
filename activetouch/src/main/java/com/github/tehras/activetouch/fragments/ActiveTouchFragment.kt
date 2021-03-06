package com.github.tehras.activetouch.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.github.tehras.activetouch.R
import com.github.tehras.activetouch.R.anim.abc_grow_fade_in_from_bottom
import com.github.tehras.activetouch.R.anim.abc_shrink_fade_out_from_bottom
import com.github.tehras.activetouch.touchlisteners.ActiveTouchBehavior
import java.io.Serializable

/**
 * Created by tehras on 7/19/16.
 *
 * This is the fragment that will come up
 * This will contain the view that needs to be passed into
 */
class ActiveTouchFragment : Fragment() {

    companion object Factory {
        val BUNDLE_BUILDER = "bundle_builder_key"
        val BUNDLE_CALLBACK = "bundle_callback"

        fun getInstance(b: ActiveTouchBehavior.Factory.ActiveTouchBuilder, callback: OnLoadHelper): ActiveTouchFragment {
            val args = Bundle()
            args.putSerializable(BUNDLE_BUILDER, b)
            args.putSerializable(BUNDLE_CALLBACK, callback)
            val f = ActiveTouchFragment()
            f.arguments = args

            return f
        }
    }

    var builder: ActiveTouchBehavior.Factory.ActiveTouchBuilder? = null
    var callback: OnLoadHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            builder = arguments.getSerializable(BUNDLE_BUILDER) as ActiveTouchBehavior.Factory.ActiveTouchBuilder
            callback = arguments.getSerializable(BUNDLE_CALLBACK) as OnLoadHelper
        }
    }

    var viewContainer: LinearLayout? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater!!.inflate(R.layout.layout_active_touch_fragment, container, false)

        viewContainer = v.findViewById(R.id.active_touch_fragment_container) as LinearLayout
        if (builder != null) {
            if (builder!!.contentViewCallback != null) {
                addContentView(builder!!.contentViewCallback?.getView(builder?.o))
            } else if (builder!!.contentFragmentV4Callback != null) {
                addFragmentViewV4(builder!!.contentFragmentV4Callback?.getFragment(builder?.o))
            } else if (builder!!.contentFragmentCallback != null) {
                addFragmentView(builder!!.contentFragmentCallback?.getFragment(builder?.o))
            }
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (callback != null)
            callback!!.loaded(view)
    }

    private fun addFragmentViewV4(contentFragV4: Fragment?) {
        clearViewContainer()

        childFragmentManager.beginTransaction()
                .setCustomAnimations(abc_grow_fade_in_from_bottom, abc_shrink_fade_out_from_bottom)
                .add(viewContainer!!.id, contentFragV4)
                .commit()
    }

    private fun addFragmentView(contentFrag: android.app.Fragment?) {
        clearViewContainer()

        activity.fragmentManager.beginTransaction()
                .setCustomAnimations(abc_grow_fade_in_from_bottom, abc_shrink_fade_out_from_bottom)
                .add(viewContainer!!.id, contentFrag)
                .commit()
    }

    private fun clearViewContainer() {
        viewContainer!!.removeAllViews()
    }

    private fun addContentView(cView: View?) {
        clearViewContainer()
        if (cView?.parent != null) {
            val p = cView?.parent
            if (p is ViewGroup)
                p.removeView(cView)
        }
        viewContainer?.addView(cView)
    }

    interface OnLoadHelper : Serializable {
        fun loaded(v: View?)
    }

}
