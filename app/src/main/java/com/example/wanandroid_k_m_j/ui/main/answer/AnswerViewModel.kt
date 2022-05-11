package com.example.wanandroid_k_m_j.ui.main.answer

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
class AnswerViewModel : BaseViewModel() {

    private val answerRepository by lazy { AnswerRepository() }

    val answerListResult: VmLiveData<AnswerListEntity> = MutableLiveData()

    /**
     * 首页文章列表(不含置顶)
     */
    fun getAnswers(page: Int) {
        launchVmRequest({ answerRepository.getAnswers(page) }, answerListResult)
    }
}