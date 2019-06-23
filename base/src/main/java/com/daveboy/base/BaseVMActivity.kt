package com.daveboy.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProviders
import me.yokeyword.fragmentation.SupportActivity

abstract class BaseVMActivity<VM : BaseViewModel>: BaseActivity() {
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createObserver()
    }
    override fun afterSetView() {
        initVM()
    }
    abstract fun providerVMClass(): Class<VM>?
    abstract fun createObserver()
    private fun initVM() {
        providerVMClass()?.let {
            viewModel = ViewModelProviders.of(this).get(it)
        }
    }


}