package com.wanandroid.base.ext

import com.wanandroid.base.exception.AppException

/**
 * @author TangRui
 * @description: 请求回调
 * @date:2022/2/23 9:35
 */
class VmResult<T> {
    var onAppSuccess: (data: T?) -> Unit = {}
    var onAppError: (AppException) -> Unit = {}
    var onAppLoading: () -> Unit = {}
    var onAppComplete: () -> Unit = {}

    fun onAppSuccess(success: (T?) -> Unit) {
        onAppSuccess = success
    }

    fun onAppError(error: (AppException) -> Unit) {
        onAppError = error
    }

    fun onAppLoading(loading: () -> Unit) {
        onAppLoading = loading
    }

    fun onAppComplete(complete: () -> Unit) {
        onAppComplete = complete
    }
}

sealed class VmState<out T> {
    object Loading : VmState<Nothing>()
    data class Success<out T>(val data: T?) : VmState<T>()
    data class Error(val error: AppException) : VmState<Nothing>()
}