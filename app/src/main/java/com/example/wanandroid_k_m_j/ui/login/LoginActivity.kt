package com.example.wanandroid_k_m_j.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.ActivityLoginBinding
import com.example.wanandroid_k_m_j.ui.main.MainActivity
import com.example.wanandroid_k_m_j.utils.ToastAction.toast
import com.huastart.vpn.utils.fullScreen
import com.huastart.vpn.utils.log
import com.huastart.vpn.utils.singleClick
import com.wanandroid.base.BaseVmActivity
import com.wanandroid.base.ext.vmObserver

class LoginActivity : BaseVmActivity() {

    private val mViewModel by viewModels<LoginViewModel>()
    private val mViewBinding by viewBinding(ActivityLoginBinding::bind)

    override val layoutId: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mToolbar.visibility = View.GONE
        initClickEvents()
    }

    override fun createObserver() {
        mViewModel.loginResult.vmObserver(this) {
            onAppLoading { showProgress() }
            onAppSuccess {
                toast("登录成功")
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
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
}