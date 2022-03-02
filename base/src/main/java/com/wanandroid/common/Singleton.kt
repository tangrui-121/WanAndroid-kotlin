package com.wanandroid.common

/**
 * @author TangRui
 * @description: 单例
 * @date:2022/2/23 10:35
 */
abstract class Singleton<T> {
    private var mInstance: T? = null

    /**
     * 创建实例
     *
     * @return T
     */
    protected abstract fun create(): T

    fun get(): T {
        synchronized(this) {
            if (mInstance == null) {
                mInstance = create()
            }
            return mInstance!!
        }
    }
}