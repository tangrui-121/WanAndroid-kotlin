package com.example.wanandroid_k_m_j.ui.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.FragmentHomeBinding
import com.example.wanandroid_k_m_j.databinding.ItemHomeArticleBinding
import com.example.wanandroid_k_m_j.databinding.ItemHomeArticleTagsBinding
import com.example.wanandroid_k_m_j.ui.login.LoginHelper
import com.example.wanandroid_k_m_j.utils.log
import com.example.wanandroid_k_m_j.utils.singleClick
import com.example.wanandroid_k_m_j.utils.visibleOrGone
import com.wanandroid.base.BaseVmFragment
import com.wanandroid.base.adapter.BaseBindingAdapter
import com.wanandroid.base.adapter.VBViewHolder
import com.wanandroid.base.ext.vmObserver
import java.util.ArrayList

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
    private var homeArticle: ArticleEntity = ArticleEntity()

    private var page = 0
    private var collect_pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun addData() {
        mViewBinding.refresh.autoRefresh()
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mViewBinding.rvHomearticle.layoutManager = LinearLayoutManager(requireContext())
        articleAdapter = object :
            BaseBindingAdapter<ArticleDataEntity, ItemHomeArticleBinding>(homeArticle.articleList) {
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
                holder.vb.itemArticleTabTop.visibleOrGone(item.top)
                holder.vb.itemArticleTabNew.visibleOrGone(item.fresh)
                holder.vb.itemArticleTabs.visibleOrGone(item.tags.size > 0)
                if (item.tags.size > 0) {
                    // tags adapter
                    holder.vb.itemArticleTabs.layoutManager =
                        GridLayoutManager(requireContext(), item.tags.size)
                    holder.vb.itemArticleTabs.adapter = object :
                        BaseBindingAdapter<ArticleTagEntity, ItemHomeArticleTagsBinding>(item.tags) {
                        override fun createViewBinding(
                            inflater: LayoutInflater,
                            parent: ViewGroup
                        ): ItemHomeArticleTagsBinding {
                            return ItemHomeArticleTagsBinding.inflate(inflater, parent, false)
                        }

                        override fun convert(
                            holder: VBViewHolder<ItemHomeArticleTagsBinding>,
                            item: ArticleTagEntity
                        ) {
                            holder.vb.itemArticleTab.text = item.name
                        }
                    }
                }
                holder.vb.itemArticleName.text =
                    if (item.author.isNotEmpty()) item.author else item.shareUser
                holder.vb.itemArticleTime.text = item.niceDate
                holder.vb.itemArticleTitle.text = Html.fromHtml(item.title)
                holder.vb.itemArticleTips.text = "${item.superChapterName}/${item.chapterName}"
                holder.vb.itemArticleLike.isSelected = item.collect

                holder.vb.itemArticleLike.singleClick {
                    LoginHelper.loginWith(requireContext()) {
                        collect_pos = getItemPosition(item)
                        if (item.collect) {
                            mViewModel.unCollectArticle(
                                id = item.id
                            )
                        } else {
                            mViewModel.collectArticle(
                                id = item.id,
                                title = item.title,
                                link = item.link,
                                author = item.author
                            )
                        }
                    }
                }
            }
        }
        mViewBinding.rvHomearticle.adapter = articleAdapter


        mViewBinding.refresh.setOnRefreshListener {
            page = 0
            mViewModel.getArticleWithTop()
        }
        mViewBinding.refresh.setOnLoadMoreListener {
            page++
            mViewModel.getArticle(page)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun createObserver() {
        // 置顶文章
        mViewModel.topAarticleResult.vmObserver(this) {
            onAppSuccess {
                it?.let {
                    if (it.size > 0) {
                        for (bean in it) {
                            bean.top = true
                        }
                        homeArticle.articleList.addAll(it)
                    }
                }
            }
            onAppError { it.errorMsg.log() }
        }
        // 其他文章
        mViewModel.articleResult.vmObserver(this) {
            onAppSuccess {
                it?.let {
                    it.articleList.let { articles ->
                        homeArticle.articleList.addAll(articles)
                        articleAdapter.notifyDataSetChanged()
                    }
                }
                if (page == 0) {
                    mViewBinding.refresh.finishRefresh()
                } else {
                    mViewBinding.refresh.finishLoadMore()
                }
            }
            onAppError { it.errorMsg.log() }
        }

        // 收藏/取消收藏文章
        mViewModel.collectArticleResult.vmObserver(this) {
            onAppSuccess {
                val bean = homeArticle.articleList.get(collect_pos)
                bean.collect = !bean.collect
                articleAdapter.notifyItemChanged(collect_pos)
            }
            onAppError { it.errorMsg.log() }
        }
    }
}