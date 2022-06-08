@file:JvmName("IntentKt")
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package com.example.wanandroid_k_m_j.exts

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Parcelable
import java.io.Serializable


inline fun <reified T> Intent(context: Context, vararg extras: Pair<String, Any?>): Intent {
    return Intent(context, T::class.java).putExtras(*extras)
}

fun Intent(action: String, vararg extras: Pair<String, Any?>): Intent {
    return Intent(action).putExtras(*extras)
}

fun Intent(action: String, data: String): Intent {
    return Intent(action).setData(data)
}

fun Intent.putExtras(vararg extras: Pair<String, Any?>): Intent {
    for (extra in extras) {
        val value = extra.second
        val key = extra.first
        when (value) {
            null -> continue
            is Int -> this.putExtra(key, value)
            is Boolean -> this.putExtra(key, value)
            is String -> this.putExtra(key, value)
            is Parcelable -> this.putExtra(key, value)
            is Serializable -> this.putExtra(key, value)
            else -> throw IllegalArgumentException("IntentKt: put ${value.javaClass} don`t impl")
        }
    }
    return this
}

inline fun <reified T : Any> Intent.extra(name: String, default: T): T {
    val tClass = T::class
    return when {
        tClass == Int::class -> this.getIntExtra(name, default as Int) as T
        tClass == Boolean::class -> this.getBooleanExtra(name, default as Boolean) as T
        tClass == String::class -> (this.getStringExtra(name) as? T) ?: default
        Parcelable::class.java.isAssignableFrom(tClass.java) ->
            (this.getParcelableExtra(name) as? T) ?: default
        Serializable::class.java.isAssignableFrom(tClass.java) ->
            this.getSerializableExtra(name) as T
        else -> {
            throw IllegalArgumentException("IntentKt: get $tClass don`t impl")
        }
    }
}

inline fun <reified T : Any> Intent.extra(name: String): T? {
    val tClass = T::class.java
    return when {
        tClass == String::class.java -> this.getStringExtra(name) as? T
        Parcelable::class.java.isAssignableFrom(tClass) -> this.getParcelableExtra(name) as? T
        else -> {
            throw IllegalArgumentException("IntentKt: get $tClass don`t impl")
        }
    }
}

fun Intent.newTask(): Intent = this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

fun Intent.newClearTask(): Intent = this.newTask().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

fun Intent.setData(uri: String): Intent = setData(Uri.parse(uri))

fun Intent.toChooser(title: String): Intent = Intent.createChooser(this, title)

@kotlin.internal.InlineOnly
inline fun Intent.launchActivity(context: Context) = context.launchActivity(this)

fun IntentFilter(vararg actions: String): IntentFilter {
    val intentFilter = IntentFilter()
    for (action in actions) {
        intentFilter.addAction(action)
    }
    return intentFilter
}
