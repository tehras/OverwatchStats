package com.github.tehras.overwatchstats.exts

import android.util.Log
import com.github.tehras.overwatchstats.application.appContext
import io.realm.*

/**
 * Created by tehras on 8/25/16.
 */

private var realm: Realm? = null

fun getRealmInstance(): Realm? {
    if (realm == null) {
        realm = Realm.getInstance(RealmConfiguration.Builder(appContext).deleteRealmIfMigrationNeeded().build())
    }

    return realm
}


fun <T : RealmModel> Realm.getAll(clazz: Class<T>, extraQuery: RealmQuery<T>.() -> RealmQuery<T>): RealmResults<T>? {
    val results = this.where(clazz)?.extraQuery()?.findAll()

    Log.d(REALM_TAG, "results $results")

    return results
}

fun <T : RealmList<S>, S : RealmModel> T.replace(s: S, i: Int, func: S.() -> Unit) {
    getRealmInstance()?.singleTransaction {
        this@replace.removeAt(i)
        val hero = this.copyToRealm(s)
        hero.func()
        this@replace.add(i, hero)
    }
}

fun <T : RealmModel> Realm.getFirst(clazz: Class<T>, extraQuery: RealmQuery<T>.() -> RealmQuery<T>): T? {
    val result = this.where(clazz)?.extraQuery()?.findFirst()

    Log.d(REALM_TAG, "result $result")

    return result
}

fun <T : RealmModel> Realm.getFirst(clazz: Class<T>): T? {
    return this.getFirst(clazz) { this }
}

fun <T : RealmModel> Realm.getAll(clazz: Class<T>): RealmResults<T>? {
    return this.getAll(clazz) { this }
}

inline fun <T : RealmModel> T.addToRealm() {
    getRealmInstance()?.copyToRealmOrUpdate(this@addToRealm)
    Log.d(REALM_TAG, "added new object ${this}")
}

fun <S : RealmModel> S.copyField(func: S?.() -> Unit) {
    getRealmInstance()?.singleTransaction {
        this.copyToRealm(this@copyField).func()
    }
}

inline fun <S : RealmModel> createObject(javaClass: Class<S>): S? {
    return getRealmInstance()?.transaction { this.createObject(javaClass) }
}

inline fun <S : RealmModel> Realm.transaction(func: Realm.() -> S?): S? {
    synchronized(this) {
        this.beginTransaction()
        val o = this.func()
        this.commitTransaction()

        return o
    }
}

@Synchronized
fun Realm.singleTransaction(func: Realm.() -> Unit) {
    synchronized(this) {
        Log.d(REALM_TAG, "beginTransaction")
        this.beginTransaction()
        this.func()
        Log.d(REALM_TAG, "closeTransaction")
        this.commitTransaction()
    }
}

val REALM_TAG = "RealmExts"

