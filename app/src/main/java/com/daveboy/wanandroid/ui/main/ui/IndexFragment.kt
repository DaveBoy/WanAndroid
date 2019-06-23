package com.daveboy.wanandroid.ui.main.ui

import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R

class IndexFragment :BaseVMFragment<IndexViewModel>(){
    override fun providerVMClass()=IndexViewModel::class.java

    override fun createObserver() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_index
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initData() {
        viewModel.getArticleList()
    }
}