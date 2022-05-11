package com.example.wanandroid_k_m_j.ui.main.answer

import com.example.wanandroid_k_m_j.data.AppBaseEntity
import com.example.wanandroid_k_m_j.network.NetworkApi

/**
 * @author TangRui
 * @description: 问答操作类
 * @link:
 * @date:2022/3/2 14:52
 */
class AnswerRepository {

    /**
     * 获取问答列表
     */
    suspend fun getAnswers(
        page: Int
    ): AppBaseEntity<AnswerListEntity> {
        return NetworkApi.getApi().getAnswers(page)
    }
}