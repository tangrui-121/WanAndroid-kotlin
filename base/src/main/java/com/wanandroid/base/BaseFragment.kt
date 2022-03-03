package com.wanandroid.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.base1.R

/**
 * @author TangRui
 * @description: Fragment基类，普通Fragment继承
 * @date:2022/2/22 17:52
 */
abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
        addData()
    }

    protected open fun initView(view: View, savedInstanceState: Bundle?) {

    }

    protected open fun addData() {

    }

    private var mDialog: Dialog? = null
    fun showProgress() {
        mDialog ?: let {
            mDialog = Dialog(requireContext())
            mDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val progressBar = ProgressBar(requireContext())
            progressBar.indeterminateDrawable =
                ContextCompat.getDrawable(requireContext(), R.drawable.progressbar)
            mDialog?.setContentView(progressBar)
        }
        mDialog?.show()
    }

    fun dismissProgress() {
        mDialog?.dismiss()
    }
}