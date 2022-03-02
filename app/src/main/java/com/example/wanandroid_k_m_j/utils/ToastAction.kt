package com.example.wanandroid_k_m_j.utils

import androidx.annotation.StringRes
import com.hjq.toast.ToastUtils

/**
 * @author TangRui
 * @description:
 * @date:2022/2/24 9:53
 */
object ToastAction {

    fun toast(text: CharSequence?) {
        ToastUtils.show(text)
    }

    fun toast(@StringRes id: Int) {
        ToastUtils.show(id)
    }

    fun toast(`object`: Any?) {
        ToastUtils.show(`object`)
    }
}