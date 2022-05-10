@file:JvmName("ViewKt")

package com.example.wanandroid_k_m_j.exts

import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun WindowInsetsCompat.safelyInsets(): Insets {
    return this.getInsets(
        WindowInsetsCompat.Type.displayCutout() or
                WindowInsetsCompat.Type.systemBars()
    )
}

fun View.applyWindowInsets(listener: (insets: WindowInsetsCompat) -> Unit) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        listener.invoke(insets)
        return@setOnApplyWindowInsetsListener insets
    }
}
