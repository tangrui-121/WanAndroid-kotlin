package com.wanandroid.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import java.lang.reflect.ParameterizedType

/**
 * @author TangRui
 * @description:
 * @link:
 * @date:2022/3/1 16:50
 */
class VBViewHolder<VB : ViewBinding>(val vb: VB, view: View) : BaseViewHolder(view)

abstract class BaseBindingAdapter<VB : ViewBinding, T>(data: MutableList<T>? = null) :
    BaseQuickAdapter<T, VBViewHolder<VB>>(0, data) {

    //重写返回自定义 ViewHolder
    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): VBViewHolder<VB> {
        //这里为了使用简洁性，使用反射来实例ViewBinding
        val vbClass: Class<VB> =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val inflate = vbClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        val mBinding =
            inflate.invoke(null, LayoutInflater.from(parent.context), parent, false) as VB
        return VBViewHolder(mBinding, mBinding.root)
    }
}