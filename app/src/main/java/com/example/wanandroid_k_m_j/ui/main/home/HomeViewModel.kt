package com.example.wanandroid_k_m_j.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wanandroid.base.BaseViewModel
import com.wanandroid.base.ext.*
import com.wanandroid.common.BaseEntity
import kotlinx.coroutines.async
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

    val articleResult0: VmLiveData<ArticleEntity0> = MutableLiveData()
    val articleResult: VmLiveData<ArticleEntity> = MutableLiveData()
    val topAarticleResult: VmLiveData<ArrayList<ArticleDataEntity>> = MutableLiveData()
    val collectArticleResult: VmLiveData<Any> = MutableLiveData()

    fun <T> BaseViewModel.launchVmRequest(
        request1: suspend () -> BaseEntity<ArticleEntity>,
        request2: suspend () -> BaseEntity<ArrayList<ArticleDataEntity>>,
        viewState: VmLiveData<ArticleEntity0>
    ) {
        viewModelScope.launch {
            runCatching {
                viewState.value = VmState.Loading
                val async1 = async {
                    request1
                }
                val async2 = async {
                    request2
                }
            }.onSuccess {
                viewState.paresVmResult(it)
            }.onFailure {
                viewState.paresVmException(it)
            }
        }
    }

    /**
     * 首页文章列表
     */
    fun getArticle() {
        launchVmRequest(
            { homeRepository.getTopArticle() },
            { homeRepository.getArticle(0) },
            articleResult0
        )
    }

    /**
     * 首页文章列表
     */
    fun getArticle(page: Int) {
        if (page == 0) {
            launchVmRequest({
                homeRepository.getTopArticle()
            }, topAarticleResult)
        }
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