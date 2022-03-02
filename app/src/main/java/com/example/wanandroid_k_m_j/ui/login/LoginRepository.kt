package com.example.wanandroid_k_m_j.ui.login

import com.example.wanandroid_k_m_j.data.AppBaseEntity
import com.example.wanandroid_k_m_j.network.NetworkApi

/**
 * @author TangRui
 * @description: 登录操作类
 * @date:2022/2/23 16:54
 */
class LoginRepository {

    suspend fun login(account:String,pwd:String):AppBaseEntity<UserEntity>{
        return NetworkApi.getApi().login(account,pwd)
    }

    fun insertUser(userEntity: UserEntity){
        //数据库操作
    }
}