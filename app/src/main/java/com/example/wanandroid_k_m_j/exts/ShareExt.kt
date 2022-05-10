package com.example.wanandroid_k_m_j.exts

import android.content.Context
import android.content.SharedPreferences
import com.example.wanandroid_k_m_j.WanAndroidApp
import com.example.wanandroid_k_m_j.exts.ShareExt.preferences

object ShareExt {

    val preferences: SharedPreferences by lazy {
        val context = WanAndroidApp.context
        context.getSharedPreferences("common_eval_sdk_shared", Context.MODE_PRIVATE)
    }
}

fun Context.getString(key: String, defaultValue: String? = null): String? =
    preferences.getString(key, defaultValue)


fun Context.getStringSet(
    key: String,
    defaultValue: MutableSet<String>? = null
): MutableSet<String>? =
    preferences.getStringSet(key, defaultValue)

fun Context.getInt(key: String, defaultValue: Int = 0): Int =
    preferences.getInt(key, defaultValue)

fun Context.getBoolean(key: String, defaultValue: Boolean = false): Boolean =
    preferences.getBoolean(key, defaultValue)

fun Context.getLong(key: String, defaultValue: Long = 0L) = preferences.getLong(key, defaultValue)

fun Context.putStringSet(key: String, value: MutableSet<String>?) =
    preferences.edit().putStringSet(key, value).apply()

fun Context.putString(key: String, value: String?) =
    preferences.edit().putString(key, value).apply()

fun Context.putInt(key: String, value: Int) = preferences.edit().putInt(key, value).apply()

fun Context.putLong(key: String, value: Long) = preferences.edit().putLong(key, value).apply()

fun Context.putBoolean(key: String, value: Boolean) =
    preferences.edit().putBoolean(key, value).apply()

fun Context.clean() = preferences.edit().clear()

fun Context.remove(key: String) = preferences.edit().remove(key)