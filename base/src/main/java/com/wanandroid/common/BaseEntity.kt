package com.wanandroid.common

import androidx.annotation.Keep

/**
 * @author TangRui
 * @description: 默认实体类包装
 * @date:2022/2/23 10:27
 */

@Keep
open class BaseEntity<T>(
    var code: Int = 1,
    var message: String = "",
    var data: T?
) {
    override fun toString(): String {
        return this.toJsonString()
    }

    /**
     * 返回是否正确
     */
    open fun dataRight(): Boolean {
        return code == 0
    }

    /**
     * 获取错误信息
     */
    open fun getMsg(): String {
        return message
    }
}