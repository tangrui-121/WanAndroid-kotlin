package com.wanandroid.base.exception

import com.wanandroid.base.ext.parseErrorString

/**
 * @author TangRui
 * @description: 错误
 * @date:2022/2/22 18:21
 */
class AppException : Exception {

    var errorMsg: String

    constructor(error: String?) : super(error) {
        errorMsg = error ?: parseError(null)
    }

    constructor(throwable: Throwable?) : super(throwable) {
        errorMsg = parseError(throwable)
    }

    private fun parseError(throwable: Throwable?): String {
        return throwable.parseErrorString()
    }
}