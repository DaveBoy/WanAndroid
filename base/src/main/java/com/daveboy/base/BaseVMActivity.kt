package com.daveboy.base

import androidx.lifecycle.ViewModelProvider
import com.daveboy.base.util.getVmClazz

abstract class BaseVMActivity<VM : BaseViewModel>: BaseActivity() {
    protected val viewModel: VM by lazy {
        initVM()
    }
    override fun afterSetView() {
        startObserve()
        super.afterSetView()
    }
    abstract fun startObserve()
    private fun initVM(): VM {
        return ViewModelProvider(this).get(getVmClazz(this) as Class<VM>)
    }


}