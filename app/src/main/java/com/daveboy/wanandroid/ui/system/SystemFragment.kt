package com.daveboy.wanandroid.ui.system

import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.common.util.startActivityExt
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.SystemModel
import com.daveboy.wanandroid.ui.system.content.SystemContentWrapperActivity
import kotlinx.android.synthetic.main.fragment_system.*

class SystemFragment :BaseVMFragment<SystemViewModel>(){

    private val adapter: SystemAdapter by lazy { SystemAdapter() }
    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initView() {
        system_rv.layoutManager=LinearLayoutManager(activity)
        adapter.onAttachedToRecyclerView(system_rv)
        system_rv.adapter=adapter.apply {
            setOnItemChildClickListener { adapter, view, position ->
                when(view.id){
                    R.id.system_des->{
                        startActivityExt<SystemContentWrapperActivity>("data" to adapter.data[position])
                    }
                    else->{
                        if(view is TextView){
                            val index=(adapter.data[position] as SystemModel).children.indexOfFirst {
                                it.name==view.text
                            }
                            startActivityExt<SystemContentWrapperActivity>("data" to adapter.data[position],"index" to if(index<0) 0 else index)
                            }
                    }
                }
            }
        }

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