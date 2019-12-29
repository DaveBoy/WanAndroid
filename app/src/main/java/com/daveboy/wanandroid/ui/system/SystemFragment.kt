package com.daveboy.wanandroid.ui.system

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.wanandroid.R
import kotlinx.android.synthetic.main.fragment_system.*

class SystemFragment :BaseVMFragment<SystemViewModel>(){

    private val adapter:SystemAdapter by lazy { SystemAdapter() }
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
            systemList.observe(this@SystemFragment, Observer {
                smart_ly.finishRefresh()
                parseState(it,{adapter.setNewData(it) })
            })
        }
        
    }


}