package com.example.wanandroid_k_m_j.ui.main.answer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.drake.brv.utils.setup
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.FragmentAnswerBinding
import com.example.wanandroid_k_m_j.databinding.ItemAnswerBinding
import com.example.wanandroid_k_m_j.databinding.ItemCommonTagsBinding
import com.example.wanandroid_k_m_j.exts.applyWindowInsets
import com.example.wanandroid_k_m_j.exts.safelyInsets
import com.example.wanandroid_k_m_j.ui.webview.SimpleWebviewActivity
import com.wanandroid.base.BaseVmFragment
import com.wanandroid.base.ext.vmObserver

class AnswerFragment : BaseVmFragment() {

    override val layoutId: Int get() = R.layout.fragment_answer

    private var page = 1

    private val mViewModel by viewModels<AnswerViewModel>()
    private val mViewBinding by viewBinding(FragmentAnswerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.root.applyWindowInsets {
            val insets = it.safelyInsets()
            mViewBinding.root.setPadding(0, insets.top, 0, 0)
        }
    }

    override fun addData() {
        mViewModel.getAnswers(page)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        initRv()
        mViewBinding.brvRefresh.apply {
            onRefresh {
                page = 1
                mViewModel.getAnswers(page)
            }
            onLoadMore {
                mViewModel.getAnswers(page)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun createObserver() {
        mViewModel.answerListResult.vmObserver(this) {
            onAppSuccess {
                it?.let {
                    mViewBinding.brvRefresh.addData(data = it.datas, hasMore = { true })
                    page++
                }
            }
        }
    }

    private fun initRv() {
        mViewBinding.rvAnswers.setup {
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