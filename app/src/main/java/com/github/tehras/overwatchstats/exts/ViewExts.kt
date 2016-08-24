package com.github.tehras.overwatchstats.exts

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils

/**
 * Created by tehras on 8/24/16.
 */

fun View.getCenterX(): Int {
    return (this.left + this.right) / 2
}

fun View.getCenterY(): Int {
    return (this.top + this.bottom) / 2
}


fun View.show() {
    // get the center for the clipping circle
    val cx = (this.left + this.right) / 2
    val cy = (this.top + this.bottom) / 2

    show(cx, cy)
}

fun View.show(cx: Int, cy: Int) {
    // get the final radius for the clipping circle
    val finalRadius = Math.max(this.width, this.height)

    // create the animator for this view (the start radius is zero)
    val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy,
            0.toFloat(), finalRadius.toFloat())
    anim.duration = 300

    // make the view visible and start the animation
    this.visibility = View.VISIBLE
    anim.start()
}

fun View.hide() {
    // get the center for the clipping circle
    val cx = (this.left + this.right) / 2
    val cy = (this.top + this.bottom) / 2

    hide(cx, cy)
}

// To hide a previously visible view using this effect:
fun View.hide(x: Int, y: Int) {

    // get the initial radius for the clipping circle
    val initialRadius = this.width

    // create the animation (the final radius is zero)
    val anim = ViewAnimationUtils.createCircularReveal(this, x, y,
            initialRadius.toFloat(), 0.toFloat())
    anim.duration = 300

    // make the view invisible when the animation is done
    anim.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            this@hide.visibility = View.GONE
        }
    })

    // start the animation
    anim.start()
}
