package com.example.wanandroid_k_m_j.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wanandroid_k_m_j.data.AppBaseEntity
import com.wanandroid.base.BaseViewModel
import com.wanandroid.base.exception.AppException
import com.wanandroid.base.ext.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.ArrayList

/**
 * @author TangRui
 * @description:
 * @link:
 * @date:2022/3/2 14:50
 */
class HomeViewModel : BaseViewModel() {

    private val homeRepository by lazy { HomeRepository() }

    val articleResult: VmLiveData<ArticleEntity> = MutableLiveData()
    val collectArticleResult: VmLiveData<Any> = MutableLiveData()

    /**
     * 首页文章列表(含置顶)
     */
    val articleResult_page0: MutableLiveData<VmState<ArticleEntity0>> = MutableLiveData()
    fun getArticleWithTop() {
        viewModelScope.launch {
            var isSuccess1 = true
            var isSuccess2 = true
            articleResult_page0.postValue(VmState.Loading)
            val articleEntity0 = ArticleEntity0()
            val t1 = async { homeRepository.getTopArticle() }
            val t2 = async { homeRepository.getArticle(0) }
            var res1: AppBaseEntity<ArrayList<ArticleDataEntity>>? = null
            var res2: AppBaseEntity<ArticleEntity>? = null
            try {
                res1 = t1.await()
            } catch (e: Exception) {
                isSuccess1 = false
            }
            try {
                res2 = t2.await()
            } catch (e: Exception) {
                isSuccess2 = false
            }
            if (!isSuccess1 && !isSuccess2) {
                articleResult_page0.postValue(VmState.Error(AppException("所有请求皆失败")))
            } else {
                articleEntity0.toparticleList = res1?.data!!
                articleEntity0.articleList = res2?.data!!
                articleResult_page0.postValue(VmState.Success(articleEntity0))
            }
        }
    }

    /**
     * 首页文章列表(不含置顶)
     */
    fun getArticle(page: Int) {
        launchVmRequest({ homeRepository.getArticle(page) }, articleResult)
    }

    /**
     * 收藏文章
     */
    fun collectArticle(
        id: Long,
        title: String,
        link: String,
        author: String
    ) {
        launchVmRequest(
            { homeRepository.collectArticle(id, title, link, author) },
            collectArticleResult
        )
    }

    /**
     * 收藏文章
     */
    fun unCollectArticle(
        id: Long
    ) {
        launchVmRequest({ homeRepository.unCollectArticle(id) }, collectArticleResult)
    }
}