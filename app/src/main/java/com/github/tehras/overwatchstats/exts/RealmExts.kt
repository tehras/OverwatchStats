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

fun <T : RealmList<S>, S : RealmModel> T.replace(s: S, i: Int) {
    getRealmInstance()?.singleTransaction {
        this@replace.removeAt(i)
        this@replace.add(i, this.copyToRealm(s))
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

fun <T : RealmModel> T.addToRealm() {
    getRealmInstance()?.executeTransaction {
        getRealmInstance()?.copyToRealmOrUpdate(this)
        Log.d(REALM_TAG, "added new object ${this}")
    }
}

fun <S : RealmModel> S.copyField(func: S?.() -> Unit) {
    getRealmInstance()?.singleTransaction {
        this.copyToRealm(this@copyField).func()
    }
}

fun <S : RealmModel> createObject(javaClass: Class<S>): S? {
    return getRealmInstance()?.transaction { this.createObject(javaClass) }
}

fun <S : RealmModel> Realm.transaction(func: Realm.() -> S?): S? {
    this.beginTransaction()
    val o = this.func()
    this.commitTransaction()

    return o
}

fun Realm.singleTransaction(func: Realm.() -> Unit) {
    this.beginTransaction()
    this.func()
    this.commitTransaction()
}

val REALM_TAG = "RealmExts"

