package com.example.wanandroid_k_m_j.network

import com.example.wanandroid_k_m_j.data.AppBaseEntity
import com.example.wanandroid_k_m_j.ui.login.UserEntity
import com.example.wanandroid_k_m_j.ui.main.home.ArticleDataEntity
import com.example.wanandroid_k_m_j.ui.main.home.ArticleEntity
import com.example.wanandroid_k_m_j.ui.main.home.BannerData
import retrofit2.http.*
import java.util.ArrayList

/**
 * @author TangRui
 * @description:
 * @date:2022/2/23 17:32
 */
interface NetApiService {
    @POST(Urls.LOGIN)
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): AppBaseEntity<UserEntity>

    @GET(Urls.ARTICLE)
    suspend fun getArticle(@Path("page") page: Int): AppBaseEntity<ArticleEntity>

    @GET(Urls.TOP_ARTICLE)
    suspend fun getTopArticle(): AppBaseEntity<ArrayList<ArticleDataEntity>>

    @GET("banner/json")
    suspend fun getBanner(): AppBaseEntity<ArrayList<BannerData>>

    @POST(Urls.Collect_Article)
    @FormUrlEncoded
    suspend fun <T> collectArticle(
        @Path("id") id: Long,
        @Field("title") title: String,
        @Field("link") link: String,
        @Field("author") author: String
    ): AppBaseEntity<T>

    @POST(Urls.unCollect_Article)
    suspend fun <T> unCollectArticle(
        @Path("id") id: Long
    ): AppBaseEntity<T>

}