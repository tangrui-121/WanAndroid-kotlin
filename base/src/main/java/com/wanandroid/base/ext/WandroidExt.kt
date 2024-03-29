package com.wanandroid.base.ext

import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.example.base1.R
import com.wanandroid.base.BaseViewModel
import com.wanandroid.base.exception.AppException
import com.wanandroid.common.BaseEntity
import com.wanandroid.common.ContextHolder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @author TangRui
 * @description:
 * @date:2022/2/23 9:31
 */

fun Throwable?.parseErrorString(): String {
    return when (this) {
        is ConnectException -> ContextHolder.getInstance().context.getString(R.string.ConnectException)
        is UnknownHostException -> ContextHolder.getInstance().context.getString(R.string.UnknownHostException)
        else -> ContextHolder.getInstance().context.getString(R.string.ElseNetException)
    }
}


@MainThread
inline fun <T> VmLiveData<T>.vmObserver(owner: LifecycleOwner, vmResult: VmResult<T>.() -> Unit) {
    val result = VmResult<T>()
    result.vmResult()
    observe(owner = owner) {
        when (it) {
            is VmState.Loading -> {
                result.onAppLoading()
            }
            is VmState.Success -> {
                result.onAppSuccess(it.data);result.onAppComplete()
            }
            is VmState.Error -> {
                result.onAppError(it.error);result.onAppComplete()
            }
        }
    }
}


/**
 * net request
 * @param request request method
 * @param viewState request result
 */
fun <T> BaseViewModel.launchVmRequest(
    request: suspend () -> BaseEntity<T>,
    viewState: VmLiveData<T>
) {
    viewModelScope.launch {
        runCatching {
            viewState.value = VmState.Loading
            request()
        }.onSuccess {
            viewState.paresVmResult(it)
        }.onFailure {
            viewState.paresVmException(it)
        }
    }
}

/**
 * net request
 * @param request request method
 */
fun <T> BaseViewModel.launchRequestNoState(request: suspend () -> BaseEntity<T>) {
    viewModelScope.launch {
        runCatching {
            request()
        }
    }
}


/**
 * 以协程形式执行
 */
fun BaseViewModel.launchBlock(block: () -> Unit) {
    viewModelScope.launch { block() }
}


/**
 * 处理返回值
 *
 * @param result 请求结果
 */
fun <T> VmLiveData<T>.paresVmResult(result: BaseEntity<T>) {
    value = if (result.dataRight()) VmState.Success(result.data) else
        VmState.Error(AppException(result.getMsg()))
}


/**
 * 异常转换异常处理
 */
fun <T> VmLiveData<T>.paresVmException(e: Throwable) {
    this.value = VmState.Error(AppException(e))
}

@MainThread
inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<T> {
    val wrappedObserver = Observer<T> { t -> onChanged.invoke(t) }
    observe(owner, wrappedObserver)
    return wrappedObserver
}
typealias VmLiveData<T> = MutableLiveData<VmState<T>>