package com.github.tehras.overwatchstats.exts

import android.util.Log
import com.github.tehras.overwatchstats.BuildConfig
import com.github.tehras.overwatchstats.R
import com.github.tehras.overwatchstats.models.champs.ConfigChamps
import com.github.tehras.overwatchstats.models.configChamps
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.*

var sFirebaseRemoteConfig: FirebaseRemoteConfig? = null
val TAG: String = "FirebaseExt"
val CHAMPS_KEY: String = "champs"
val CHAMP_KEY: String = "champ_"

/**
 * Created by tehras on 8/21/16.
 *
 * sFirebaseRemoteConfig
 */
@Suppress("UNUSED")
fun getInstance(): FirebaseRemoteConfig? {
    if (sFirebaseRemoteConfig == null) {
        sFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        sFirebaseRemoteConfig?.setConfigSettings(FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEBUG).build())
        sFirebaseRemoteConfig?.setDefaults(R.xml.remote_configs_defaults)
    }

    return sFirebaseRemoteConfig
}


@Suppress("UNUSED")
fun FirebaseRemoteConfig.initializeChamps() {
    //get count
    var count = this.getString(CHAMPS_KEY).toInt()

    //lets get all the champs now
    while (count > 0) {
        if (configChamps == null)
            configChamps = ArrayList()

        configChamps!!.add(ConfigChamps(this.getString("$CHAMP_KEY$count")))

        count--
    }
}

@Suppress("UNUSED")
fun FirebaseRemoteConfig.fetchConfigs(): FirebaseRemoteConfig {
    // cacheExpirationSeconds is set to cacheExpiration here, indicating that any previously
// fetched and cached config would be considered expired because it would have been fetched
// more than cacheExpiration seconds ago. Thus the next fetch would go to the server unless
// throttling is in progress. The default expiration duration is 43200 (12 hours).
    this.fetch(43200)?.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            Log.d(TAG, "Fetch succeeded")

            // Once the config is successfully fetched it must be activated before newly fetched
            // values are returned.
            this.activateFetched()
            //reinitialize champs
            this.initializeChamps()
        } else {
            Log.d(TAG, "Fetch failed")
        }
    }

    return this
}