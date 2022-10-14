package com.example.wanandroid_k_m_j.ui.main.answer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.drake.brv.PageRefreshLayout
import com.drake.brv.utils.linear
import com.drake.brv.utils.page
import com.drake.brv.utils.setup
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.data.AppBaseEntity
import com.example.wanandroid_k_m_j.databinding.FragmentAnswerBinding
import com.example.wanandroid_k_m_j.databinding.ItemAnswerBinding
import com.example.wanandroid_k_m_j.databinding.ItemCommonTagsBinding
import com.example.wanandroid_k_m_j.exts.applyWindowInsets
import com.example.wanandroid_k_m_j.exts.safelyInsets
import com.example.wanandroid_k_m_j.ui.main.home.ArticleEntity
import com.example.wanandroid_k_m_j.ui.webview.SimpleWebviewActivity
import com.wanandroid.base.BaseVmFragment
import com.wanandroid.base.ext.vmObserver
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun PageRefreshLayout.addDataWithHasMore(data: List<Any>?, hasMore: Boolean? = null) {
    if (hasMore != null) {
        this.addData(data, hasMore = { hasMore })
    } else {
        this.addData(data, hasMore = { data != null && data.isNotEmpty() })
    }
}

class AnswerFragment : Fragment(R.layout.fragment_answer) {

    private val mViewModel by viewModels<AnswerViewModel>()
    private val mViewBinding by viewBinding(FragmentAnswerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.root.applyWindowInsets {
            val insets = it.safelyInsets()
            mViewBinding.root.setPadding(0, insets.top, 0, 0)
        }
        initRv()
        mViewBinding.brvRefresh.apply {
            onRefresh {
                lifecycleScope.launch {
                    val data = async { AnswerRepository().getAnswers(index) }.await()
                    if (!data.dataRight()) {
                        return@launch
                    }
                    data.data?.let {
                        addDataWithHasMore(it.datas)
                    }
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        mViewBinding.brvRefresh.autoRefresh()
        mViewBinding.rvAnswers.scrollToPosition(0)
    }

    private fun initRv() {
        mViewBinding.rvAnswers.linear().setup {
            addType<AnswersEntity>(R.layout.item_answer)
            onBind {
                val bind = ItemAnswerBinding.bind(this.itemView)
                val item = getModel<AnswersEntity>()
                bind.itemAnswerTitle.text = item.title
                bind.itemAnswerName.text = "作者：${item.author}"
                bind.itemAnswerTips.text = "分类：${item.chapterName}/${item.superChapterName}"
                bind.itemAnswerTime.text = "时间：${item.niceShareDate}"
                bind.itemAnswerTabs.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                bind.itemAnswerTabs.setup {
                    addType<Tag>(R.layout.item_common_tags)
                    onBind {
                        val childBind = ItemCommonTagsBinding.bind(this.itemView)
                        val childItem = getModel<Tag>()
                        childBind.itemTabname.text = childItem.name
                    }
                }.models = item.tags
            }
            onClick(R.id.item_answer_like) {

            }
            onClick(R.id.layout_item) {
                val item = getModel<AnswersEntity>()
                SimpleWebviewActivity.start(
                    requireContext(),
                    item.link
                )
            }
        }
    }
}