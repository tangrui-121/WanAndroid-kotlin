package com.example.wanandroid_k_m_j.data

import com.example.wanandroid_k_m_j.constant.WanAndroidConstant.REQUEST_RESULT_SUCCESS
import com.wanandroid.common.BaseEntity

/**
 * @author TangRui
 * @description:
 * @date:2022/2/23 16:55
 */
class AppBaseEntity<T>(private var errorCode: Int, private var errorMsg: String, data: T) :
    BaseEntity<T>(errorCode, errorMsg, data) {

    override fun dataRight(): Boolean {
        return errorCode == REQUEST_RESULT_SUCCESS
    }

    override fun getMsg(): String {
        return errorMsg
    }
}