package com.example.wanandroid_k_m_j.weight

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

/**
 * @author TangRui
 * @description:
 * @link: 禁用水平滑动的ViewPager（一般用于 APP 首页的 ViewPager + Fragment）
 * @date:2022/2/24 14:22
 */
class NoScrollViewPager @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null) :
    ViewPager(context, attrs) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // 不拦截这个事件
        return false
    }

    @Suppress("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        // 不处理这个事件
        return false
    }

    override fun executeKeyEvent(event: KeyEvent): Boolean {
        // 不响应按键事件
        return false
    }

    override fun setCurrentItem(item: Int) {
        // 只有相邻页才会有动画
        super.setCurrentItem(item, abs(currentItem - item) == 1)
    }
}