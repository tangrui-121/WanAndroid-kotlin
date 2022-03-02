package com.example.wanandroid_k_m_j.utils

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

/**
 * @author TangRui
 * @description: ViewBinding相关扩展
 * @link: https://juejin.cn/post/6906153878312452103
 * @date:2022/3/1 17:14
 */
inline fun <reified VB : ViewBinding> Activity.inflate() = lazy {
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

inline fun <reified VB : ViewBinding> Dialog.inflate() = lazy {
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB