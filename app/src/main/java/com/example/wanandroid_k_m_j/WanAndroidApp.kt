package com.example.wanandroid_k_m_j

import android.app.Application
import com.example.wanandroid_k_m_j.utils.ToastStyle
import com.hjq.toast.ToastUtils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


/**
 * @author TangRui
 * @description:
 * @date:2022/2/24 11:25
 */
class WanAndroidApp: Application() {


    override fun onCreate() {
        super.onCreate()
        initSdk(this)
    }

    companion object {

        fun initSdk(application: Application){
            // 初始化吐司
            ToastUtils.init(application, ToastStyle())
            // 设置调试模式
            ToastUtils.setDebugMode(BuildConfig.DEBUG)

            Logger.addLogAdapter(AndroidLogAdapter())
        }

    }
}