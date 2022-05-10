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

    suspend fun getBanner(): AppBaseEntity<ArrayList<BannerData>> {
        return NetworkApi.getApi().getBanner()
    }

    suspend fun getTopArticle(): AppBaseEntity<ArrayList<ArticleDataEntity>> {
        return NetworkApi.getApi().getTopArticle()
    }

    suspend fun <T> collectArticle(
        id: Long,
        title: String,
        link: String,
        author: String
    ): AppBaseEntity<T> {
        return NetworkApi.getApi().collectArticle(id, title, link, author)
    }

    suspend fun <T> unCollectArticle(
        id: Long
    ): AppBaseEntity<T> {
        return NetworkApi.getApi().unCollectArticle(id)
    }
}