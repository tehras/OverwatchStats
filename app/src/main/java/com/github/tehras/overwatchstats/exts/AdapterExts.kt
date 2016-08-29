package com.github.tehras.overwatchstats.exts

import android.content.Context
import android.support.annotation.IntegerRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.models.configChamps

/**
 * Created by tehras on 8/27/16.
 *
 * For Adapter Helpers
 */

fun ViewGroup.ladingHolder(): LoadingHolder {
    return LoadingHolder(this.inflate(R.layout.adapter_loading_layout))
}

fun ViewGroup.titleHolder(text: Int, image: Int): TitleHolder {
    return TitleHolder(this.inflate(R.layout.adapter_title_layout), text, image)
}

@IntegerRes
fun ViewGroup.errorHolder(text: Int): ErrorHolder {
    return ErrorHolder(this.inflate(R.layout.adapter_error_layout), text)
}

@IntegerRes
fun ViewGroup.inflate(layout: Int): View {
    return LayoutInflater.from(this.context).inflate(layout, this, false)
}

class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
}


class TitleHolder(itemView: View, text: Int, image: Int) : RecyclerView.ViewHolder(itemView) {
    init {
        (itemView.findViewById(R.id.adapter_title) as TextView).text = itemView.context.resources.getText(text)
        (itemView.findViewById(R.id.adapter_title_image) as ImageView).setImageResource(image)
    }
}

class ErrorHolder(itemView: View, val text: Int) : RecyclerView.ViewHolder(itemView) {
    init {
        (itemView.findViewById(R.id.error_text) as TextView).text = itemView.context.resources.getText(text)
        (itemView.findViewById(R.id.error_icon) as ImageView).setImageResource(randomResources(context = itemView.context))
    }

    private fun randomResources(context: Context): Int {
        return context.resources?.getIdentifier("ic_${configChamps?.get((Math.random() * 22.toDouble()).toInt())?.name?.toLowerCase() ?: "bastion"}", "drawable", context.packageName)?.toInt() ?: 0
    }
}