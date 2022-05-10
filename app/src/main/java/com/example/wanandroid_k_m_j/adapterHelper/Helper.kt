package com.example.wanandroid_k_m_j.adapterHelper

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setAdapterX(adapter: RecyclerView.Adapter<*>?) {
    this.adapter = RecyclerWithHeadAndFootAdapter(adapter)
}

fun RecyclerView.addHeaderView(header: View) {
    (this.adapter as RecyclerWithHeadAndFootAdapter<*>?)?.addHeaderView(header)
        ?: throw IllegalAccessException("Adapter is not RecyclerWithHeadAndFootAdapter")
}

fun RecyclerView.resetHeaderViews() {
    (this.adapter as RecyclerWithHeadAndFootAdapter<*>?)?.resetHeaderViews()
        ?: throw IllegalAccessException("Adapter is not RecyclerWithHeadAndFootAdapter")
}

fun RecyclerView.getHeaderViewCount(): Int {
    return (this.adapter as RecyclerWithHeadAndFootAdapter<*>?)?.getHeadersCount()
        ?: throw IllegalAccessException("Adapter is not RecyclerWithHeadAndFootAdapter")
}

fun RecyclerView.addFooterView(footer: View) {
    (this.adapter as RecyclerWithHeadAndFootAdapter<*>?)?.addFootView(footer)
        ?: throw IllegalAccessException("Adapter is not RecyclerWithHeadAndFootAdapter")
}

fun RecyclerView.resetFooterViews() {
    (this.adapter as RecyclerWithHeadAndFootAdapter<*>?)?.resetFooterViews()
        ?: throw IllegalAccessException("Adapter is not RecyclerWithHeadAndFootAdapter")
}

fun RecyclerView.getFooterViewCount(): Int {
    return (this.adapter as RecyclerWithHeadAndFootAdapter<*>?)?.getFootersCount()
        ?: throw IllegalAccessException("Adapter is not RecyclerWithHeadAndFootAdapter")
}