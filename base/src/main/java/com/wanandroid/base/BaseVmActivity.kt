package com.wanandroid.base

import android.os.Bundle

/**
 * @author TangRui
 * @description: ViewModelActivity基类，ViewModelActivity继承
 * @date:2022/2/22 18:03
 */
abstract class BaseVmActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createObserver()
    }

    /**
     * 创建观察者
     */
    abstract fun createObserver()
}