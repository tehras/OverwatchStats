package com.github.tehras.overwatchstats.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.github.tehras.overwatchstats.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

/**
 * Created by tehras on 8/20/16.
 */
class UserImageView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    private lateinit var mBackground: ImageView
    private lateinit var mOutline: ImageView

    init {
        View.inflate(context, R.layout.view_user_icon, this)

        mBackground = findViewById(R.id.icon_background) as ImageView
        mOutline = findViewById(R.id.icon_outline) as ImageView
    }

    @Suppress("UNUSED") fun populateImages(iconUrl: String?, outlineUrl: String?) {
        if (iconUrl != null)
            Picasso.with(context).load(iconUrl).transform(RoundedCornersTransformation(12, 0)).into(mBackground)
        if (outlineUrl != null)
            Picasso.with(context).load(outlineUrl).centerCrop().into(mOutline)

    }

}
