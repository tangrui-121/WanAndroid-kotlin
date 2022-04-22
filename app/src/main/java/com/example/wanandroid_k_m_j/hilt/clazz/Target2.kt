package com.example.wanandroid_k_m_j.hilt.clazz

import android.app.Activity
import android.content.Context

class Target2(
    val context: Context,
    val activity: Activity,
    val target3: Target3
) {

    fun print() {
        println("${this.javaClass.simpleName}\n$context\n$activity\n")
        target3.print()
    }
}