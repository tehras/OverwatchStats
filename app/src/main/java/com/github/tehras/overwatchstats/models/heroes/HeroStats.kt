package com.github.tehras.overwatchstats.models.heroes

import io.realm.RealmList
import io.realm.RealmObject
import java.io.Serializable

/**
 * Created by tehras on 8/21/16.
 *
 * Hero Stats
 */
open class HeroStats : Serializable, RealmObject() {
    var map: RealmList<HeroMap>? = null

}

open class HeroMap : RealmObject() {
    var key: String? = null
    var value: String? = null
}
