package com.example.wanandroid_k_m_j.ui.main

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.ActivityMainBinding
import com.example.wanandroid_k_m_j.ui.main.nav.MainTab
import com.huastart.vpn.utils.singleClick
import com.wanandroid.base.BaseActivity

class MainActivity : BaseActivity() {

    private val mViewBinding by viewBinding(ActivityMainBinding::bind)

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    fun initView() {
        mViewBinding.rvHomeNavigation.layoutManager = GridLayoutManager(this, 4)
        mViewBinding.rvHomeNavigation.adapter =
            MianTabAdapter(
                this,
                listOf(
                    MainTab("首页", R.drawable.home_home_selector, true),
                    MainTab("首页1", R.drawable.home_home_selector, false),
                    MainTab("首页2", R.drawable.home_home_selector, false),
                    MainTab("首页3", R.drawable.home_home_selector, false)
                )
            )

//        mViewBinding.vpHomePager.
    }
}

class MianTabAdapter(val activity: MainActivity, val list: List<MainTab>) :
    BaseQuickAdapter<MainTab, BaseViewHolder>
        (R.layout.home_navigation_item, list as MutableList<MainTab>) {
    override fun convert(holder: BaseViewHolder, item: MainTab) {
        val title = holder.getView<TextView>(R.id.tv_home_navigation_title)
        val img = holder.getView<ImageView>(R.id.iv_home_navigation_icon)
        val itemView = holder.itemView
        title.text = item.title
        title.setTextColor(if (item.checked) activity.resources.getColor(R.color.purple_500) else activity.resources.getColor(R.color.black))
        img.background = ContextCompat.getDrawable(activity, item.imgRes)
        img.isSelected = item.checked
        itemView.singleClick {
            for ((index, e) in list.withIndex()) {
                e.checked = index == getItemPosition(item)
            }
            notifyDataSetChanged()
        }
    }
}