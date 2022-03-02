package com.example.wanandroid_k_m_j.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.wanandroid.base.BaseViewModel
import com.wanandroid.base.ext.VmLiveData
import com.wanandroid.base.ext.launchVmRequest

/**
 * @author TangRui
 * @description:
 * @link:
 * @date:2022/3/2 14:50
 */
class HomeViewModel : BaseViewModel() {
    private val homeRepository by lazy { HomeRepository() }

    val articleResult: VmLiveData<ArticleEntity> = MutableLiveData()
    val topAarticleResult: VmLiveData<ArticleEntity> = MutableLiveData()

    fun getArticle(page: Long) {
        launchVmRequest({ homeRepository.getArticle(page) }, articleResult)
    }

    fun getTopArticle() {
        launchVmRequest({ homeRepository.getTopArticle() }, topAarticleResult)
    }
}