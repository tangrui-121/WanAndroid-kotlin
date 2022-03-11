package com.wanandroid.base

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.base1.R
import com.wanandroid.base.utils.immersive
import com.wanandroid.base.utils.statusPadding

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
        val appBackground = findViewById<LinearLayout>(R.id.appBackground)
        appBackground.statusPadding()
        val viewContent = findViewById<FrameLayout>(R.id.viewContent)
        LayoutInflater.from(this).inflate(layoutId, viewContent)
        initView(savedInstanceState)
        initHeaderView()
        initToolBar()
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
}