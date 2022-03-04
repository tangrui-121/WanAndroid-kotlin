package com.example.wanandroid_k_m_j.ui.login

import androidx.lifecycle.MutableLiveData
import com.wanandroid.base.BaseViewModel
import com.wanandroid.base.ext.VmLiveData
import com.wanandroid.base.ext.launchVmRequest

/**
 * @author TangRui
 * @description:
 * @date:2022/2/23 16:52
 */
class LoginViewModel : BaseViewModel() {

    // 用MutableLiveData将VmState封装 ---  A
    // vmObserver提供LifecycleOwner以及VmResult的扩展，VmResult提供各状态的操作赋值
    // 最终调用系统LiveData的Observer来达成监听
    // 在通过BaseViewModel.launchVmRequest来完成对A对网络请求的监听,开启协程
    // launchVmRequest入参：一个返回BaseEntity的挂起函数、LiveData

    private val loginRepository by lazy { LoginRepository() }
    // LiveData MutableLiveData
    // https://www.cnblogs.com/guanxinjing/p/11544273.html
    val loginResult: VmLiveData<UserEntity> = MutableLiveData()

    fun login(account: String, pwd: String) {
        launchVmRequest({ loginRepository.login(account, pwd) }, loginResult)
    }

    fun saveUser(userEntity: UserEntity) {
        loginRepository.insertUser(userEntity)
    }
}