package com.che300.common_eval_sdk.packages.auction

import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.widget.TextView

/**
 * 标红文字的通用build类
 */
class SpanBuilder : SpannableStringBuilder {

    constructor() : super()
    constructor(text: CharSequence?) : super(text)

    private val flag: Int = SpannableString.SPAN_INCLUSIVE_EXCLUSIVE

    /***
     * 添加带有样式的文字
     */
    fun add(text: CharSequence?, vararg whats: Any): SpanBuilder {
        if (text.isNullOrEmpty()) {
            return this
        }

        val start = length
        append(text)
        for (what in whats) {
            setSpan(what, start, length, flag)
        }
        return this
    }

    fun add(text: CharSequence?): SpanBuilder {
        if (text.isNullOrEmpty()) {
            return this
        }

        append(text)
        return this
    }

    fun add(text: Char): SpanBuilder {
        append(text)
        return this
    }

    fun build(tvText: TextView?) {
        if (tvText == null) {
            return
        }

        tvText.text = this
    }

    /***
     *设置上文字并设置点击
     */
    fun buildAndSetClick(tvText: TextView?) {
        build(tvText)
        tvText?.movementMethod = LinkMovementMethod.getInstance()
        tvText?.highlightColor = Color.TRANSPARENT
    }
}