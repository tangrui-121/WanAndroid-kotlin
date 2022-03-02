package com.example.wanandroid_k_m_j.ui.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.FragmentHomeBinding
import com.example.wanandroid_k_m_j.databinding.ItemHomeArticleBinding
import com.example.wanandroid_k_m_j.utils.log
import com.example.wanandroid_k_m_j.utils.visibleOrGone
import com.wanandroid.base.BaseVmFragment
import com.wanandroid.base.adapter.BaseBindingAdapter
import com.wanandroid.base.adapter.VBViewHolder
import com.wanandroid.base.ext.vmObserver

private const val ARG_PARAM1 = "param1"

class HomeFragment : BaseVmFragment() {

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    private val mViewModel by viewModels<HomeViewModel>()
    private val mViewBinding by viewBinding(FragmentHomeBinding::bind)
    private var param1: String? = null

    // 文章适配器
    private lateinit var articleAdapter: BaseBindingAdapter<ArticleDataEntity, ItemHomeArticleBinding>

    // 总文章，由置顶文章和其他文章组成
    private var homrArticle: ArticleEntity = ArticleEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
        mViewModel.getArticle(0)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mViewBinding.rvHomearticle.layoutManager = LinearLayoutManager(requireContext())
        articleAdapter = object :
            BaseBindingAdapter<ArticleDataEntity, ItemHomeArticleBinding>(homrArticle.articleList) {
            override fun createViewBinding(
                inflater: LayoutInflater,
                parent: ViewGroup
            ): ItemHomeArticleBinding {
                return ItemHomeArticleBinding.inflate(inflater, parent, false)
            }

            override fun convert(
                holder: VBViewHolder<ItemHomeArticleBinding>,
                item: ArticleDataEntity
            ) {
                holder.vb.itemArticleTabNew.visibleOrGone(item.fresh)
                holder.vb.itemArticleTab.visibleOrGone(item.tags.size > 0)
                if (item.tags.size > 0) holder.vb.itemArticleTab.text = item.tags[0].name
                holder.vb.itemArticleName.text =
                    if (item.author.isNotEmpty()) item.author else item.shareUser
                holder.vb.itemArticleTime.text = item.niceDate
                holder.vb.itemArticleTitle.text = item.title
                holder.vb.itemArticleTips.text = "${item.superChapterName}/${item.chapterName}"
                holder.vb.itemArticleLike.isSelected = item.collect
            }
        }
        mViewBinding.rvHomearticle.adapter = articleAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun createObserver() {
        // 置顶文章
        mViewModel.topAarticleResult.vmObserver(this) {
            onAppLoading { showProgress() }
            onAppSuccess {
                it.articleList.let { articles -> homrArticle.articleList.addAll(articles) }
            }
            onAppComplete { dismissProgress() }
            onAppError { it.errorMsg.log() }
        }
        // 其他文章
        mViewModel.articleResult.vmObserver(this) {
            onAppLoading { showProgress() }
            onAppSuccess {
                it.articleList.let { articles ->
                    homrArticle.articleList.addAll(articles)
                    articleAdapter.notifyDataSetChanged()
                }
            }
            onAppComplete { dismissProgress() }
            onAppError { it.errorMsg.log() }
        }
    }
}