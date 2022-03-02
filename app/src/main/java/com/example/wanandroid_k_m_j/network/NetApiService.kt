package com.example.wanandroid_k_m_j.network

import com.example.wanandroid_k_m_j.data.AppBaseEntity
import com.example.wanandroid_k_m_j.ui.login.UserEntity
import com.example.wanandroid_k_m_j.ui.main.home.ArticleEntity
import retrofit2.http.*

/**
 * @author TangRui
 * @description:
 * @date:2022/2/23 17:32
 */
interface NetApiService {
    @POST(Urls.LOGIN)
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String): AppBaseEntity<UserEntity>

    @GET(Urls.ARTICLE)
    suspend fun getArticle(@Path("page") page: Long): AppBaseEntity<ArticleEntity>

    @GET(Urls.TOP_ARTICLE)
    suspend fun getTopArticle(): AppBaseEntity<ArticleEntity>
}