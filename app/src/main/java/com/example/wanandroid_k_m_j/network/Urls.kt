package com.example.wanandroid_k_m_j.network

/**
 * @author TangRui
 * @description:
 * @date:2022/2/23 17:33
 */
object Urls {

    /**
     * 接口地址
     */
    const val SERVER_URL = "https://wanandroid.com/"
    /**
     * 登录
     */
    const val LOGIN = "user/login"
    /**
     * 文章列表
     */
    const val ARTICLE = "article/list/{page}/json"
    /**
     * 置顶文章列表
     */
    const val TOP_ARTICLE = "article/top/json"

    /**
     * 收藏文章
     */
    const val Collect_Article = "lg/collect/user_article/update/{id}/json"

    /**
     * 取消收藏文章
     */
    const val unCollect_Article = "lg/uncollect_originId/{id}/json"
}