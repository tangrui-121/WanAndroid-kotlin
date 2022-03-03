package com.example.wanandroid_k_m_j.ui.main.home

import com.example.wanandroid_k_m_j.data.AppBaseEntity
import com.example.wanandroid_k_m_j.network.NetworkApi
import java.util.ArrayList

/**
 * @author TangRui
 * @description: 首页操作类
 * @link:
 * @date:2022/3/2 14:52
 */
class HomeRepository {

    suspend fun getArticle(page: Int): AppBaseEntity<ArticleEntity> {
        return NetworkApi.getApi().getArticle(page)
    }

    suspend fun getTopArticle(): AppBaseEntity<ArrayList<ArticleDataEntity>> {
        return NetworkApi.getApi().getTopArticle()
    }
}