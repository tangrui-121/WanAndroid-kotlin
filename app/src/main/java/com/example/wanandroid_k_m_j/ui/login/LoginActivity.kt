package com.example.wanandroid_k_m_j.ui.login

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.ActivityLoginBinding
import com.example.wanandroid_k_m_j.exts.log
import com.example.wanandroid_k_m_j.exts.singleClick
import com.example.wanandroid_k_m_j.helper.MvvmHelper.MVVM_KEY_ACCOUNT
import com.example.wanandroid_k_m_j.helper.MvvmHelper.getMvvmNormal
import com.example.wanandroid_k_m_j.utils.ToastAction.toast
import com.wanandroid.base.BaseVmActivity
import com.wanandroid.base.ext.vmObserver
import com.wanandroid.base.utils.immersive

class LoginActivity : BaseVmActivity() {

    private val mViewModel by viewModels<LoginViewModel>()
    private val mViewBinding by viewBinding(ActivityLoginBinding::bind)

    override val layoutId: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mToolbar.visibility = View.GONE
        immersive(darkMode = true)
        initClickEvents()
    }

    override fun createObserver() {
        mViewModel.loginResult.vmObserver(this) {
            onAppLoading { showProgress() }
            onAppSuccess {
                getMvvmNormal()
                    .encode(MVVM_KEY_ACCOUNT, mViewBinding.edAccount.text.toString())
                toast("登录成功")
                finish()
            }
            onAppComplete { dismissProgress() }
            onAppError { it.errorMsg.log() }
        }
    }

    private fun initClickEvents() {
        mViewBinding.tvLogin.singleClick {
            mViewModel.login(
                mViewBinding.edAccount.text.toString(),
                mViewBinding.edPwd.text.toString()
            )
        }
    }

    override fun finish() {
        LoginHelper.performCallback()
        super.finish()
    }
}