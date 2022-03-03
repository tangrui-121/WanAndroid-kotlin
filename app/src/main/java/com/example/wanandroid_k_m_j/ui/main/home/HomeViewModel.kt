package com.example.wanandroid_k_m_j.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wanandroid.base.BaseViewModel
import com.wanandroid.base.ext.*
import com.wanandroid.common.BaseEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    fun getArticle(page: Int) {
        if (page == 0) {
            launchVmRequest({
                homeRepository.getTopArticle()
            }, topAarticleResult)
        }
        launchVmRequest({ homeRepository.getArticle(page) }, articleResult)

        viewModelScope.launch {
            val articleAsync = async {
                homeRepository.getTopArticle()
            }
            val topAarticleAsync = async {
                homeRepository.getArticle(page)
            }
            articleAsync.await()
            topAarticleAsync.await()
        }
    }

    fun <T> BaseViewModel.launchVmRequest2(
        request1: suspend () -> BaseEntity<T>,
        equest2: suspend () -> BaseEntity<T>,
        viewState: VmLiveData<T>
    ) {
        viewModelScope.launch {
            runCatching {
                viewState.value = VmState.Loading
                val async1 = async {
                    request1
                }
                val async2 = async {
                    equest2
                }
                async1.await()
                async2.await()
            }.onSuccess {
//                viewState.paresVmResult(it)
            }.onFailure {
                viewState.paresVmException(it)
            }
        }
    }

}