package com.wanandroid.base

import android.os.Bundle

/**
 * @author TangRui
 * @description: ViewModelFragment基类，ViewModelFragment继承
 * @date:2022/2/22 18:07
 */
abstract class BaseVmFragment :BaseFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createObserver()
    }


    /**
     * 创建观察者
     */
    abstract fun createObserver()
}