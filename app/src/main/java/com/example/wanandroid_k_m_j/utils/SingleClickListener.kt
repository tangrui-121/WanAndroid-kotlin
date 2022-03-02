package com.example.wanandroid_k_m_j.utils

import android.view.View

/**
 * 单击
 */
internal abstract class SingleClickListener : View.OnClickListener {
    private val delay = 500
    private var lastClickTime: Long = 0L
    protected abstract fun onSingleClick(v: View?)

    override fun onClick(v: View?) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > delay) {
            lastClickTime = currentTime
            onSingleClick(v)
        }
    }
}