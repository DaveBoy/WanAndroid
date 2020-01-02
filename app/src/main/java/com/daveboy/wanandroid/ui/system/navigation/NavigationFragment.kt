package com.daveboy.wanandroid.ui.system.navigation

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.wanandroid.R
import kotlinx.android.synthetic.main.fragment_system.*

class NavigationFragment :BaseVMFragment<NavigationViewModel>(){

    private val adapter: NavigationAdapter by lazy { NavigationAdapter() }
    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initView() {
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
            navigationList.observe(this@NavigationFragment, Observer {
                smart_ly.finishRefresh()
                parseState(it,{adapter.setNewData(it) })
            })
        }
    }


}