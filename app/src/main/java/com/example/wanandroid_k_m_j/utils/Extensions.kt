package com.example.wanandroid_k_m_j.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.example.wanandroid_k_m_j.BuildConfig
import com.example.wanandroid_k_m_j.constant.WanAndroidConstant.HUB_LOG_TAG
import java.util.ArrayList
import java.util.regex.Pattern

/**
 * 项目统一log
 */
internal fun Any?.log() {
    if (BuildConfig.DEBUG) {
        Log.i(HUB_LOG_TAG, toString())
    }
}

internal fun Any?.httpLog() {
    if (BuildConfig.DEBUG) {
        Log.i("http_log", toString())
    }
}


/**
 * 项目统一toast
 * 默认LENGTH_SHORT
 */
//fun String.toast(duration: Int = Toast.LENGTH_SHORT) {
//    Toast.makeText(FarmingApp.mContext, this, Toast.LENGTH_SHORT).show()
//}

//fun TextView.checkBlank(message: String): String? {
//    val text = this.text.toString()
//    if (text.isBlank()) {
//        message.toast()
//        return null
//    }
//    return text
//}

fun String?.setNullText(): String {
    return if (this == null || this == "null") return "" else this
}


/**
 * 沉浸式
 */
internal fun Activity.fullScreen() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //透明状态栏
        val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.decorView.systemUiVisibility = option
        window.statusBarColor = Color.TRANSPARENT
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )

}

internal fun View.gone() {
    visibility = View.GONE
}

internal fun View.invisible() {
    visibility = View.INVISIBLE
}

internal fun View.visible() {
    visibility = View.VISIBLE
}

internal fun View.visibleOrGone(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

/**
 * 单击
 */
internal fun View.singleClick(f: (v: View?) -> Unit) {
    setOnClickListener(object : SingleClickListener() {
        override fun onSingleClick(v: View?) {
            f.invoke(v)
        }
    })
}

fun String?.isNull(): Boolean = this.isNullOrBlank() || "null" == this || "" == this

fun String.getAllSatisfyStr(regex: String?): ArrayList<String>? {

    if (this.isNull()) {
        return null
    }
    val allSatisfyStr = ArrayList<String>()
    if (regex.isNull()) {
        allSatisfyStr.add(this)
        return allSatisfyStr
    }
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this)
    while (matcher.find()) {
        allSatisfyStr.add(matcher.group())
    }
    return allSatisfyStr
}