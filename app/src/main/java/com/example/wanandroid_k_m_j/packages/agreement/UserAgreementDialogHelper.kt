package com.example.wanandroid_k_m_j.packages.agreement

import android.app.Activity
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.TextView
import com.che300.common_eval_sdk.packages.auction.ClickSpan
import com.che300.common_eval_sdk.packages.auction.SpanBuilder
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.exts.getStringSet
import com.example.wanandroid_k_m_j.exts.putStringSet
import com.example.wanandroid_k_m_j.weight.DialogBuilder

object UserAgreementDialogHelper {

    private const val SP_SET_KEY = "user_agreement_set"

    fun check(attach: Activity, done: (() -> Unit)? = null) {
        val set = attach.getStringSet(SP_SET_KEY, mutableSetOf()) ?: mutableSetOf()
        val className = attach.javaClass.name
        if (set.contains(className)) {
            return
        }

        val inflate = LayoutInflater.from(attach).inflate(R.layout.dialog_user_agreenment, null)
        SpanBuilder().add("使用本服务表明您已同意\n")
            .add("《用户协议》", ClickSpan(attach, "https://kdj.ceshi.che300.com/static_page/ALEUserAgreement.html", "用户协议"))
            .buildAndSetClick(inflate.findViewById(R.id.tv_agreement))
        val cbAgreement = inflate.findViewById<CheckBox>(R.id.cb_agreement)
        inflate.findViewById<TextView>(R.id.cb_agreement_desc).setOnClickListener { cbAgreement.isChecked = !cbAgreement.isChecked }
        DialogBuilder(attach)
            .title("提示")
            .cancelable(false)
            .contentView(inflate)
            .showCancelButton(false)
            .sureText("确认")
            .onSureClickListener {
                done?.invoke()
                if (cbAgreement.isChecked) {
                    set.add(className)
                    attach.putStringSet(SP_SET_KEY, set)
                }
            }
            .show()
    }
}