package com.example.wanandroid_k_m_j.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.wanandroid_k_m_j.exts.isNull
import com.example.wanandroid_k_m_j.helper.MvvmHelper.MVVM_KEY_ACCOUNT
import com.example.wanandroid_k_m_j.helper.MvvmHelper.getMvvmNormal

/**
 * @author TangRui
 * @description: 登录帮助类
 * @link:
 * @date:2022/3/4 15:31
 */
abstract class LoginCallBack @JvmOverloads constructor() {

    /**
     * 登录回调。
     * 已经登录，直接回调；未登录，跳转到登录页，登录成功后回调
     */
    abstract fun isLogin()

    /**
     * 未登录登录取消后回调
     */
    open fun cancel() {}
}

object LoginHelper {

    private var loginCallBack: LoginCallBack? = null

    fun performCallback() {
        val loginCallBack = loginCallBack
        if (loginCallBack == null) {
            return
        }
        if (isLogin()) {
            loginCallBack.isLogin()
        } else {
            loginCallBack.cancel()
        }
        LoginHelper.loginCallBack = null
    }


    @JvmStatic
    fun loginWith(context: Context?, isLogin: () -> Unit) {
        loginWith(context, object : LoginCallBack() {
            override fun isLogin() {
                isLogin.invoke()
            }
        })
    }

    @JvmStatic
    fun isLogin() = !getMvvmNormal()
        .decodeString(MVVM_KEY_ACCOUNT).isNull()

    @JvmStatic
    fun loginWith(context: Context?, callback: LoginCallBack?) {
        if (context == null) return
        val login = isLogin()
        if (login) {
            callback?.isLogin()
        } else {
            this.loginCallBack = callback
            val intent = getLoginIntent(context)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    fun getLoginIntent(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }
}
