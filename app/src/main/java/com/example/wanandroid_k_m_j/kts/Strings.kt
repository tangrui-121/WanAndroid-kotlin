package com.example.wanandroid_k_m_j.kts

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.Spanned
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.che300.common_eval_sdk.packages.auction.ClickSpan
import com.che300.common_eval_sdk.packages.auction.SpanBuilder
import java.util.regex.Pattern

fun TextView.getString(): String = text.toString()

fun TextView.getInt(): Int = text.toString().safeToInt()

fun TextView.getFloat(): Float = text.toString().safeToFloat()

fun TextView.getDouble(): Double = text.toString().toDouble()

/**
 * Html，使用原生html解析器，自定义的有可能会定制
 */
fun String.toHtml(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_OPTION_USE_CSS_COLORS)
} else {
    Html.fromHtml(this)
}

fun String.color(color: Int): String = if (this.isNotEmpty()) "<font color=\"#" +
        Integer.toHexString(color) + "\">" + this + "</font>" else ""

fun String.color(colorStr: String): String = if (this.isNotEmpty()) "<font color=\"" +
        colorStr + "\">" + this + "</font>" else ""

fun String.color(context: Context, colorRes: Int): String {
    val string = Integer.toHexString(ContextCompat.getColor(context, colorRes))
    return color("#" + string.substring(string.length - 6, string.length))
}

/**
 * 删除线
 */
fun String.del(): String = "<del>$this</del>"

/**
 * 加粗
 */
fun String.strong(): String = "<strong>$this</strong>"

fun String?.parseColor(): Int =
    if (this.notNull())
        try {
            Color.parseColor(this)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            Color.TRANSPARENT
        }
    else
        Color.TRANSPARENT

fun String?.safeToInt(): Int {
    this ?: return 0
    return try {
        this.toInt()
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        0
    }
}

fun String?.safeToLong(): Long {
    this ?: return 0
    return try {
        this.toLong()
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        0
    }
}

fun String?.safeToFloat(): Float {
    this ?: return 0f
    return try {
        this.toFloat()
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        0f
    }
}

fun String?.safeToDouble(): Double {
    this ?: return 0.0
    return try {
        this.toDouble()
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        0.0
    }
}

fun String?.toBrandIcon(): String {
    return "http://assets.che300.com/theme/images/brand/large/b${this}.jpg"
}

private fun String.encode(): String {
    return Uri.encode(this)
//        return URLEncoder.encode(this, "utf-8")
}

private val regexTrimZero = Regex("0+?$")
private val regexTrimDot = Regex("[.]$")

fun String.trimZeroAndDot(): String {
    var s = this
    if (s.indexOf(".") > 0) {
        // 去掉多余的0
        s = s.replace(regexTrimZero, "")
        // 如最后一位是.则去掉
        s = s.replace(regexTrimDot, "")
    }
    return s
}

fun String.trimDot(): String {
    if (this.endsWith(".")) {
        return this.substring(0, this.length - 1)
    }
    return this
}

fun String.appendURLParams(vararg pairs: Pair<String, Any?>): String {
    if (pairs.isEmpty()) return this
    val sb = StringBuilder(this)
    if (!this.contains("?")) {
        sb.append("?")
    } else {
        sb.append("&")
    }
    for (pair in pairs) {
        sb.append(pair.first.encode())
            .append("=")
        val v = pair.second
        if (v != null) {
            sb.append(v.toString().encode())
        }
        sb.append("&")
    }
    sb.deleteCharAt(sb.length - 1)
    return sb.toString()
}

/**
 * 正则匹配，返回ArrayList<String>
 * @param regex 正则
 */
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

/**
 * 遍历处理多个隐私协议的高亮和超链接
 * @param activity 上下文
 * @param targets 需要处理文案的集合
 * @param links 网址集合
 */
fun String.changeTextinView(activity: Activity, targets: List<String>, links: List<String>): SpanBuilder {
    var startSpan = 0
    var endSpan = 0
    var span: SpanBuilder = SpanBuilder().add(this)
    for ((index,e) in targets.withIndex()) {
        startSpan = this.indexOf(e, endSpan)
        val clickapsn = ClickSpan(activity, links.get(index), null)
        // Need a NEW span object every loop, else it just moves the span
        if (startSpan < 0) break
        endSpan = startSpan + e.length
        span.setSpan(
            clickapsn, startSpan, endSpan,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return span
}