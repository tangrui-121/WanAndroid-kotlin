package com.example.wanandroid_k_m_j.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.ActivityMainBinding
import com.example.wanandroid_k_m_j.exts.applyWindowInsets
import com.example.wanandroid_k_m_j.exts.safelyInsets
import com.example.wanandroid_k_m_j.exts.singleClick
import com.example.wanandroid_k_m_j.gotoflutter.Go2FlutterActivity
import com.example.wanandroid_k_m_j.livedata_activityresult.TakePhotoActivity
import com.example.wanandroid_k_m_j.ui.main.answer.AnswerFragment
import com.example.wanandroid_k_m_j.ui.main.home.HomeFragment
import com.example.wanandroid_k_m_j.ui.main.mine.MineFragment
import com.example.wanandroid_k_m_j.ui.main.nav.MainTab
import com.wanandroid.base.BaseActivity
import com.wanandroid.base.utils.immersive
import io.flutter.embedding.android.FlutterFragment
<<<<<<< HEAD

class MainActivity : BaseActivity() {

    // 这里viewBinding委托会报DrawerLayout和LinearLayout转化异常
//    private val mViewBinding by viewBinding(ActivityMainBinding::bind)

    private val mViewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
=======
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import java.util.concurrent.ConcurrentHashMap

class MainActivity : BaseActivity() {

    /**
     * 原生跳转flutter卡顿
     * 缓存FlutterEngine
     */
    companion object{
        //缓存 FlutterEngine 的 key
        const val FLUTTER_ENGINE_ID = "default"
    }
    //FlutterEngine
    private lateinit var flutterEngine: FlutterEngine

    private val getACallback = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){

        }
    }

    private val mViewBinding by viewBinding(ActivityMainBinding::bind)
>>>>>>> 1a9b8af7c4f2028828a8f26bcfd096a82c805b2b

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val tabList: MutableList<MainTab> = ArrayList()
    private lateinit var homeNavigationAdapter: MianTabAdapter

    init {
        tabList.add(MainTab("首页", R.drawable.home_home_selector, true))
        tabList.add(MainTab("首页1", R.drawable.home_home_selector, false))
        tabList.add(MainTab("首页2", R.drawable.home_home_selector, false))
        tabList.add(MainTab("首页3", R.drawable.home_home_selector, false))

        fragmentList.add(HomeFragment())
        fragmentList.add(AnswerFragment())
        fragmentList.add(FlutterFragment())
        fragmentList.add(MineFragment())
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initBar()
        initView()
        initDrawerLayout()
    }

    private fun initBar() {
        mToolbar.visibility = View.GONE
        immersive(darkMode = true)
        mViewBinding.root.applyWindowInsets {
            val insets = it.safelyInsets()
            // 顶部安全距离Fragment内处理
            mViewBinding.root.setPadding(insets.left, 0, insets.right, insets.bottom)
        }
    }

    private fun initView() {
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
        mViewBinding.vpHomePager.isUserInputEnabled = false
    }

    private fun initDrawerLayout() {
        mViewBinding.ivDrawer.singleClick {
            mViewBinding.dlCrashDrawer.openDrawer(GravityCompat.START)
        }
        mViewBinding.toFlutter.singleClick {
            startActivity(Intent(this, Go2FlutterActivity::class.java))
        }
        mViewBinding.toLivedataAcresult.singleClick {
            startActivity(Intent(this, TakePhotoActivity::class.java))
        }
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
            if (item.checked) activity.resources.getColor(R.color.purple_500) else activity.resources.getColor(
                R.color.black
            )
        )
        img.background = ContextCompat.getDrawable(activity, item.imgRes)
        img.isSelected = item.checked
    }
}