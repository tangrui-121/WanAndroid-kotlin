package com.che300.common_eval_sdk.packages.auction

import android.content.Context
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import com.example.wanandroid_k_m_j.exts.color
import com.example.wanandroid_k_m_j.ui.webview.SimpleWebviewActivity
import com.example.wanandroid_k_m_j.ui.webview.SimpleWebviewActivity.Companion.EXTRA_TITLE
import com.example.wanandroid_k_m_j.ui.webview.SimpleWebviewActivity.Companion.EXTRA_URL
import org.jetbrains.anko.startActivity

/**
 * 超链接，点击跳H5
 * 蓝色
 */
class ClickSpan(val context: Context?, val url: String?, val title: String? = null) :
    ClickableSpan() {
    var enable = true
    override fun onClick(widget: View) {
        if (enable) {
            enable = false
            context?.startActivity<SimpleWebviewActivity>(EXTRA_URL to url, EXTRA_TITLE to title)
            widget.postDelayed({ enable = true }, 800)
        }
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = false
        ds.color = context?.color(com.example.base1.R.color.common_confirm_text_color) ?: -13988609
    }
}