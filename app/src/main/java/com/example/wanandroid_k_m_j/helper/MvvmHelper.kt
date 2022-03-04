package com.example.wanandroid_k_m_j.helper

import com.tencent.mmkv.MMKV

/**
 * @author TangRui
 * @description:
 * @link:
 * @date:2022/3/4 17:08
 */
object MvvmHelper {

    // mvvm文件名
    const val MVVM_NORMAL = "mvvm_normal"

    // key
    const val MVVM_KEY_ACCOUNT = "mvvm_key_account"

    fun getMvvmNormal(): MMKV {
        return MMKV.mmkvWithID(MVVM_NORMAL, MMKV.MULTI_PROCESS_MODE)
    }

}