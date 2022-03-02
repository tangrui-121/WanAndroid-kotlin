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

    private val loginRepository by lazy { LoginRepository() }
    val loginResult: VmLiveData<UserEntity> = MutableLiveData()

    fun login(account: String, pwd: String) {
        launchVmRequest({ loginRepository.login(account, pwd) }, loginResult)
    }

    fun saveUser(userEntity: UserEntity) {
        loginRepository.insertUser(userEntity)
    }
}