package com.example.wanandroid_k_m_j.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.ActivityMainv2Binding
import com.example.wanandroid_k_m_j.exts.applyWindowInsets
import com.example.wanandroid_k_m_j.exts.safelyInsets
import com.example.wanandroid_k_m_j.ui.main.answer.AnswerFragment
import com.example.wanandroid_k_m_j.ui.main.home.HomeFragment
import com.example.wanandroid_k_m_j.ui.main.mine.MineFragment
import com.example.wanandroid_k_m_j.utils.WindowEdgeManager

class MainActivityV2 : AppCompatActivity(R.layout.activity_mainv2) {

    private val mViewBinding by viewBinding(ActivityMainv2Binding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowEdgeManager(this).applyEdgeToEdge(window)

        mViewBinding.root.applyWindowInsets {
            val insets = it.safelyInsets()
            // 顶部安全距离Fragment内处理
            mViewBinding.root.setPadding(insets.left, 0, insets.right, insets.bottom)
        }

        mViewBinding.bottomBars.setOnCheckedChangeListener { _, checkedId ->
            showFragment(checkedId)
        }
        showFragment(R.id.tab_home)
    }

    private fun showFragment(checkedId: Int) {
        val clazz = when (checkedId) {
            R.id.tab_home -> HomeFragment::class.java
            R.id.tab_after_loan -> AnswerFragment::class.java
            R.id.tab_message -> HomeFragment::class.java
            R.id.tab_mine -> MineFragment::class.java
            else -> throw IllegalArgumentException("Unknown checkedId: $checkedId")
        }
        val tag = clazz.name
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = supportFragmentManager.fragmentFactory
                .instantiate(this.classLoader, clazz.name)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
            .setPrimaryNavigationFragment(fragment)
            .commit()
    }
}