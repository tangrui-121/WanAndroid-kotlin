package com.example.wanandroid_k_m_j.hilt.inter

import javax.inject.Inject

class ISimpleImpl @Inject constructor(): ISimple {

    override fun print(string: String) {
        println(this::class.simpleName + ";" + string)
    }
}