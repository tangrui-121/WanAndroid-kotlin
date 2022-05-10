package com.example.wanandroid_k_m_j.adapterHelper

import android.view.View
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * 装饰者模式
 * recycleview头部底部布局
 * https://www.jianshu.com/p/1d075d35c5b4
 */
class RecyclerWithHeadAndFootAdapter<VH : RecyclerView.ViewHolder?>(private val innerAdapter: RecyclerView.Adapter<VH>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val BASE_ITEM_TYPE_HEADER = 100000
        private const val BASE_ITEM_TYPE_FOOTER = 200000
    }

    private val mHeaderViews = SparseArrayCompat<View>()
    private val mFootViews = SparseArrayCompat<View>()
    private val adapterDataObserver = object : RecyclerView.AdapterDataObserver() {

        override fun onChanged() {
            this@RecyclerWithHeadAndFootAdapter.notifyDataSetChanged()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            this@RecyclerWithHeadAndFootAdapter.notifyItemRangeChanged(
                positionStart + getHeadersCount(), itemCount
            )
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            this@RecyclerWithHeadAndFootAdapter.notifyItemRangeInserted(
                positionStart + getHeadersCount(), itemCount
            )
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            this@RecyclerWithHeadAndFootAdapter.notifyItemRangeChanged(
                positionStart + getHeadersCount(), itemCount, payload
            )
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            this@RecyclerWithHeadAndFootAdapter.notifyItemMoved(fromPosition, toPosition)
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            this@RecyclerWithHeadAndFootAdapter.notifyItemRangeRemoved(
                positionStart + getHeadersCount(), itemCount
            )
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        innerAdapter?.registerAdapterDataObserver(adapterDataObserver)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        innerAdapter?.unregisterAdapterDataObserver(adapterDataObserver)
    }

    private fun isHeaderViewPos(position: Int): Boolean {
        return position < getHeadersCount()
    }

    private fun isFooterViewPos(position: Int): Boolean {
        return position >= getHeadersCount() + getContentCount()
    }

    fun addHeaderView(view: View) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view)
    }

    fun resetHeaderViews() {
        mHeaderViews.clear()
    }

    fun addFootView(view: View) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view)
    }

    fun resetFooterViews() {
        mFootViews.clear()
    }

    fun getHeadersCount(): Int {
        return mHeaderViews.size()
    }

    fun getFootersCount(): Int {
        return mFootViews.size()
    }

    private fun getContentCount(): Int {
        return innerAdapter?.itemCount ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position)
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getContentCount())
        }
        val type = innerAdapter?.getItemViewType(position - getHeadersCount()) ?: 0
        if (type >= BASE_ITEM_TYPE_HEADER) {
            throw IllegalArgumentException("View type is > BASE_ITEM_TYPE_HEADER($BASE_ITEM_TYPE_HEADER)")
        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = mHeaderViews[viewType]
        if (view != null) {
            return HeaderViewHolder(view)
        }
        view = mFootViews[viewType]
        if (view != null) {
            return FooterViewHolder(view)
        }
        return innerAdapter?.onCreateViewHolder(parent, viewType) as RecyclerView.ViewHolder
    }

    override fun getItemCount(): Int {
        return getHeadersCount() + getFootersCount() + getContentCount()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return
        }
        innerAdapter?.onBindViewHolder(holder as VH, position - getHeadersCount())
    }

    private class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)

}