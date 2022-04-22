package com.example.wanandroid_k_m_j.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.hilt.clazz.Target
import com.example.wanandroid_k_m_j.hilt.clazz.Target2
import com.example.wanandroid_k_m_j.hilt.clazz.Target3
import com.example.wanandroid_k_m_j.hilt.inter.ISimple
import com.example.wanandroid_k_m_j.hilt.withdifclazz.Target4
import com.example.wanandroid_k_m_j.hilt.withdifclazz.TargetType1
import com.example.wanandroid_k_m_j.hilt.withdifclazz.TargetType2
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    @Inject
    lateinit var target: Target

    @Inject
    lateinit var target3: Target3

    @Inject
    lateinit var target2: Target2

    @Inject
    lateinit var iSimple: ISimple

    @TargetType1
    @Inject
    lateinit var target4Type1: Target4

    @TargetType2
    @Inject
    lateinit var target4Type2: Target4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt)

        target.print()
        target3.print()
        target2.print()
        iSimple.print("hhhhhh")

        println(target4Type1)
        println(target4Type2)
    }
}