package com.example.wanandroid_k_m_j.hilt.withdifclazz

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TargetType1()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TargetType2()

class Target4 {


}