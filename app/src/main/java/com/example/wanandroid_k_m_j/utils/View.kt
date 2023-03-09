@file:JvmName("HomeUtils")
package com.example.wanandroid_k_m_j.utils

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import androidx.annotation.FloatRange
import androidx.core.view.doOnAttach
import kotlin.math.max

internal const val VIEW_SATURATION = 0f

/**
 * 设置View的颜色饱和度
 * @param sat 饱和度范围 [0, 1]
 */
fun View?.setSaturation(@FloatRange(from = 0.0, to = 1.0) sat: Float) {
    if (this == null) return
    val paint = Paint()
    val colorMatrix = ColorMatrix().apply {
        this.setSaturation(kotlin.math.min(1f, max(0f, sat)))// 颜色饱和度 [0, 1]
    }
    paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
    this.doOnAttach {
        // onAttachToWindow后才可以拿到正确的硬件加速状态
        val type = if (it.isHardwareAccelerated) View.LAYER_TYPE_HARDWARE else View.LAYER_TYPE_SOFTWARE
        it.setLayerType(type, paint)
    }
}
/**
 * 获取所有子view
 */
val View?.allChildViews: List<View>
    get() {
        if (this == null) return mutableListOf()
        val allchildren = mutableListOf<View>()
        if (this is ViewGroup) {
            val vp = this
            for (i in 0 until vp.childCount) {
                val viewchild = vp.getChildAt(i)
                allchildren.add(viewchild)
                allchildren.addAll(viewchild.allChildViews)
            }
        } else allchildren.add(this)
        return allchildren
    }
