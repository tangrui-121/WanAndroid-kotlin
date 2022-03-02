package com.example.wanandroid_k_m_j.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.ActivityMainBinding
import com.example.wanandroid_k_m_j.ui.main.home.HomeFragment
import com.example.wanandroid_k_m_j.ui.main.mine.MineFragment
import com.example.wanandroid_k_m_j.ui.main.nav.MainTab
import com.wanandroid.base.BaseActivity

class MainActivity : BaseActivity() {

    private val mViewBinding by viewBinding(ActivityMainBinding::bind)

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val tabList: MutableList<MainTab> = ArrayList()
    private lateinit var homeNavigationAdapter: MianTabAdapter

    init {
        tabList.add(MainTab("首页", R.drawable.home_home_selector, true))
        tabList.add(MainTab("首页1", R.drawable.home_home_selector, false))
        tabList.add(MainTab("首页2", R.drawable.home_home_selector, false))
        tabList.add(MainTab("首页3", R.drawable.home_home_selector, false))

        fragmentList.add(HomeFragment())
        fragmentList.add(MineFragment())
        fragmentList.add(MineFragment())
        fragmentList.add(MineFragment())
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initView() {
        mViewBinding.rvHomeNavigation.layoutManager = GridLayoutManager(this, 4)
        homeNavigationAdapter = MianTabAdapter(this, tabList)
        homeNavigationAdapter.setOnItemClickListener { adapter, view, position ->
            for ((index, e) in tabList.withIndex()) {
                e.checked = index == position
            }
            adapter.notifyDataSetChanged()
            mViewBinding.vpHomePager.setCurrentItem(position, true)
        }
        mViewBinding.rvHomeNavigation.adapter = homeNavigationAdapter

        mViewBinding.vpHomePager.adapter =
            MainVpAdapter(this.supportFragmentManager,
                fragmentList,
                LifecycleRegistry(this).apply {
                    currentState = Lifecycle.State.RESUMED
                })
        mViewBinding.vpHomePager.offscreenPageLimit = 4
        mViewBinding.vpHomePager.setCurrentItem(0, false)
        mViewBinding.vpHomePager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for ((index, e) in tabList.withIndex()) {
                    e.checked = index == position
                }
                homeNavigationAdapter.notifyDataSetChanged()
            }
        })
    }
}

class MainVpAdapter(
    frgManager: FragmentManager,
    fragments: MutableList<Fragment>,
    lifecycle: Lifecycle,
) :
    FragmentStateAdapter(frgManager, lifecycle) {
    private val fragmentList: MutableList<Fragment> = fragments
    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}

class MianTabAdapter(val activity: MainActivity, list: List<MainTab>) :
    BaseQuickAdapter<MainTab, BaseViewHolder>
        (R.layout.item_main_navigation, list as MutableList<MainTab>) {
    override fun convert(holder: BaseViewHolder, item: MainTab) {
        val title = holder.getView<TextView>(R.id.tv_home_navigation_title)
        val img = holder.getView<ImageView>(R.id.iv_home_navigation_icon)
        title.text = item.title
        title.setTextColor(
            if (item.checked) activity.resources.getColor(R.color.purple_500) else activity.resources.getColor(R.color.black)
        )
        img.background = ContextCompat.getDrawable(activity, item.imgRes)
        img.isSelected = item.checked
    }
}