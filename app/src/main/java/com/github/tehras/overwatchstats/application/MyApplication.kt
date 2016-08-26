package com.github.tehras.overwatchstats.application

import android.app.Application
import android.content.Context

/**
 * Created by tehras on 8/25/16.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}

var appContext: Context? = null

