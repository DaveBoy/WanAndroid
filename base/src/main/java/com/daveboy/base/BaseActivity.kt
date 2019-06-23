package com.daveboy.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProviders
import me.yokeyword.fragmentation.SupportActivity

abstract class BaseActivity: SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        afterSetView()
        initView()
        initListener()
        initData()
    }
    open fun afterSetView (){}
    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initListener()
    abstract fun initData()
}