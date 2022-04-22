package com.example.wanandroid_k_m_j.hilt.clazz

class Target3 private constructor() {

    private lateinit var string: String

    fun print() {
        println(this.javaClass.simpleName + ":" + string)
    }

    class Builder {
        private val target = Target3();

        fun setStr(string: String): Builder {
            target.string = string
            return this
        }

        fun build(): Target3 {
            return target
        }
    }
}
