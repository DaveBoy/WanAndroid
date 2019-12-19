package com.daveboy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.daveboy.base.util.getVmClazz


abstract class BaseVMFragment<VM : BaseViewModel>: BaseFragment() {
    protected val viewModel: VM by lazy {
        initVM()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startObserve()
        super.onViewCreated(view, savedInstanceState)

    }
    abstract fun startObserve()
    private fun initVM(): VM {
        return ViewModelProvider(this).get(getVmClazz(this) as Class<VM>)
    }
}