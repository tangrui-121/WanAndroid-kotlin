package com.wanandroid.base

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.example.base1.R
import com.gyf.immersionbar.ImmersionBar

/**
 * @author TangRui
 * @description: Activity基类，普通Activity继承
 * @date: 2022/2/22 17:28
 */
abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var mToolbar: Toolbar
    private var mDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val viewContent = findViewById<FrameLayout>(R.id.viewContent)
        LayoutInflater.from(this).inflate(layoutId, viewContent)
        initView(savedInstanceState)
        initHeaderView()
        initToolBar()
//        ImmersionBar.with(this).init()
//        transparentStatusBar(this)
    }

    @get:LayoutRes
    protected abstract val layoutId: Int

    private fun initHeaderView() {
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        mToolbar.setNavigationOnClickListener { finish() }
    }

    protected open fun initToolBar() {

    }

    protected open fun initView(savedInstanceState: Bundle?) {

    }

    fun showProgress() {
        mDialog ?: let {
            mDialog = Dialog(it)
            mDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val progressBar = ProgressBar(it)
            progressBar.indeterminateDrawable =
                ContextCompat.getDrawable(it, R.drawable.progressbar)
            mDialog?.setContentView(progressBar)
        }
        mDialog?.show()
    }

    fun dismissProgress() {
        mDialog?.dismiss()
    }

    protected fun transparentStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = activity.window
            //添加Flag把状态栏设为可绘制模式
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            //view不根据系统窗口来调整自己的布局
            val mContentView = window.findViewById(Window.ID_ANDROID_CONTENT) as ViewGroup
            val mChildView = mContentView.getChildAt(0)
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false)
                ViewCompat.requestApplyInsets(mChildView)
            }
        }else{
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}