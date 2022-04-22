package com.example.wanandroid_k_m_j

import android.app.Application
import android.content.Context
import com.example.wanandroid_k_m_j.utils.ToastStyle
import com.hjq.toast.ToastUtils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates


/**
 * @author TangRui
 * @description:
 * @date:2022/2/24 11:25
 */
@HiltAndroidApp
class WanAndroidApp: Application() {

    companion object {

        var context: Context by Delegates.notNull()
            private set

        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
        initSdk(this)
    }

    fun initSdk(application: Application){
        // SmartRefresh
        initSmartRefresh()
        // 吐司
        ToastUtils.init(application, ToastStyle())
        ToastUtils.setDebugMode(BuildConfig.DEBUG)
        // Logger
        Logger.addLogAdapter(AndroidLogAdapter())
        // MMKV
        val rootDir: String = MMKV.initialize(application)
    }

    private fun initSmartRefresh(){
        // 报v4的错的话 https://blog.csdn.net/weixin_42150080/article/details/120033140
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            //layout.setPrimaryColorsId(R.color.color_FFFAFAFA, R.color.color_FF00000) //全局设置主题颜色
            ClassicsHeader(context) ////指定为经典Header，默认是 贝塞尔雷达Header
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> //指定为经典Footer，默认是 BallPulseFooter
            val classicsFooter = ClassicsFooter(context)
            classicsFooter.setDrawableSize(20f)
            //底部加载动画和布局
            //classicsFooter.visibility = View.GONE
            //内容不满一页时不能开启上拉加载功能
            layout.setEnableLoadMoreWhenContentNotFull(false)
            classicsFooter
        }
    }
}