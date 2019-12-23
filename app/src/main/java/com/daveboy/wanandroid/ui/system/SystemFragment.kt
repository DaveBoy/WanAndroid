package com.daveboy.wanandroid.ui.system

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_system.*

class SystemFragment :BaseVMFragment<SystemViewModel>(){

    override fun providerVMClass()= SystemViewModel::class.java
    private lateinit var adapter:SystemAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initView() {
        smart_ly.setEnableLoadMore(false)

        if(::adapter.isInitialized.not()){
            adapter= SystemAdapter()
        }
        system_rv.layoutManager=LinearLayoutManager(activity)
        adapter.onAttachedToRecyclerView(system_rv)
        system_rv.adapter=adapter

    }

    override fun initListener() {
        smart_ly.setOnRefreshListener {
            initData()
        }
    }

    override fun initData() {
        viewModel.getSystemList()
    }
    override fun startObserve() {
        viewModel.apply {
            systemList.observe(this@SystemFragment, Observer {
                smart_ly.finishRefresh()
                adapter.setNewData(it)
            })
        }
        
    }


}