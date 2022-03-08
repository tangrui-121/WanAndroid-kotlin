package com.example.wanandroid_k_m_j.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wanandroid_k_m_j.data.AppBaseEntity
import com.wanandroid.base.BaseViewModel
import com.wanandroid.base.ext.*
import com.wanandroid.common.BaseEntity
import kotlinx.coroutines.launch
import java.util.ArrayList

/**
 * @author TangRui
 * @description:
 * @link:
 * @date:2022/3/2 14:50
 */
class HomeViewModel : BaseViewModel() {

    private val homeRepository by lazy { HomeRepository() }

    val topAarticleResult: VmLiveData<ArrayList<ArticleDataEntity>> = MutableLiveData()
    val articleResult: VmLiveData<ArticleEntity> = MutableLiveData()
    val collectArticleResult: VmLiveData<Any> = MutableLiveData()

    /**
     * 首页文章列表(含置顶)
     */
    fun getArticleWithTop() {
        launchVmRequests(
            { homeRepository.getTopArticle() },
            { homeRepository.getArticle(0) },
            topAarticleResult,
            articleResult
        )
    }

    fun BaseViewModel.launchVmRequests(
        request1: suspend () -> AppBaseEntity<ArrayList<ArticleDataEntity>>,
        request2: suspend () -> AppBaseEntity<ArticleEntity>,
        viewState1: VmLiveData<ArrayList<ArticleDataEntity>>,
        viewState2: VmLiveData<ArticleEntity>
    ) {
        viewModelScope.launch {
            runCatching {
                viewState1.value = VmState.Loading
                request1()
            }.onSuccess {
                viewState1.paresVmResult(it)
            }.onFailure {
                viewState1.paresVmException(it)
            }

            runCatching {
                viewState2.value = VmState.Loading
                request2()
            }.onSuccess {
                viewState2.paresVmResult(it)
            }.onFailure {
                viewState2.paresVmException(it)
            }
        }
    }

    /**
     * 首页文章列表(不含置顶)
     */
    fun getArticle(page: Int) {
//        if (page == 0) {
//            launchVmRequest({
//                homeRepository.getTopArticle()
//            }, topAarticleResult)
//        }
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