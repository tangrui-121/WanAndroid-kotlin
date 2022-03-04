package com.example.wanandroid_k_m_j.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.wanandroid.base.BaseViewModel
import com.wanandroid.base.ext.*
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
    val topAarticleResult: VmLiveData<ArrayList<ArticleDataEntity>> = MutableLiveData()
    val collectArticleResult: VmLiveData<Any> = MutableLiveData()

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
        launchVmRequest({ homeRepository.collectArticle(id, title, link, author) }, collectArticleResult)
    }
}