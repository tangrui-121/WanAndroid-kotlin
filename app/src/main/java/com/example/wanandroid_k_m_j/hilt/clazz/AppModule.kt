package com.example.wanandroid_k_m_j.hilt.clazz

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@InstallIn(ActivityComponent::class)
@Module
object AppModule {

    @Provides
    fun providerTarget3(): Target3 {
        val target = Target3.Builder().setStr("nuhao").build()
        return target
    }

    @Provides
    fun providerTarget2(
        @ActivityContext context: Context,
        activity: Activity,
        target3: Target3
    ): Target2 {
        return Target2(context, activity, target3)
    }
}