package com.example.wanandroid_k_m_j.ui.main.home

import com.example.wanandroid_k_m_j.data.AppBaseEntity
import com.example.wanandroid_k_m_j.network.NetworkApi

/**
 * @author TangRui
 * @description: 首页操作类
 * @link:
 * @date:2022/3/2 14:52
 */
class HomeRepository {

    suspend fun getArticle(page: Long): AppBaseEntity<ArticleEntity> {
        return NetworkApi.getApi().getArticle(page)
    }

    suspend fun getTopArticle(): AppBaseEntity<ArticleEntity> {
        return NetworkApi.getApi().getTopArticle()
    }
}