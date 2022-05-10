package com.example.wanandroid_k_m_j.weight

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.example.wanandroid_k_m_j.R


class SizeOfRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.radioButtonStyle,
) : AppCompatRadioButton(context, attrs, defStyleAttr) {

    private var drawableWidth: Int = -1
    private var drawableHeight: Int = -1

    init {
        val arrays =
            context.obtainStyledAttributes(attrs, R.styleable.SizeOfRadioButton)
        drawableWidth =
            arrays.getDimensionPixelSize(R.styleable.SizeOfRadioButton_drawableWidth, drawableWidth)
        drawableHeight =
            arrays.getDimensionPixelSize(R.styleable.SizeOfRadioButton_drawableHeight,
                drawableHeight)
        arrays.recycle()

        buttonDrawable = null

        val compoundDrawables = compoundDrawables
        setCompoundDrawables(
            compoundDrawables[0],
            compoundDrawables[1],
            compoundDrawables[2],
            compoundDrawables[3]
        )
    }

    override fun setCompoundDrawables(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?,
    ) {
        applyDrawable(left)
        applyDrawable(top)
        applyDrawable(right)
        applyDrawable(bottom)
        super.setCompoundDrawables(left, top, right, bottom)
    }

    private fun applyDrawable(drawable: Drawable?) {
        drawable?.setBounds(0, 0,
            if (drawableWidth <= 0) drawable.intrinsicWidth else drawableWidth,
            if (drawableHeight <= 0) drawable.intrinsicHeight else drawableHeight)
    }

    override fun setButtonDrawable(buttonDrawable: Drawable?) {
        if (buttonDrawable != null) {
            throw IllegalArgumentException("请使用setCompoundDrawables()设置图标")
        }
        super.setButtonDrawable(buttonDrawable)
    }

}