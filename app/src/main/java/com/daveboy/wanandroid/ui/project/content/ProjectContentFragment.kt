package com.daveboy.wanandroid.ui.project.content

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.smart_ly
import kotlinx.android.synthetic.main.fragment_tab_project.*

class ProjectContentFragment  : BaseVMFragment<ContentViewModel>(){
    private lateinit var adapter:ContentAdapter
    override fun providerVMClass(): Class<ContentViewModel>? {
        return ContentViewModel::class.java
    }

    override fun startObserve() {
        viewModel.apply {
            tabProjectResponse.observe(this@ProjectContentFragment, Observer {
                smart_ly.finishRefresh()
                smart_ly.finishLoadMore()
                smart_ly.setEnableLoadMore(it.over.not())
                if(page.value==2){
                    adapter.setNewData(it.datas)
                }else{
                    adapter.addData(it.datas)
                }
            })

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tab_project
    }

    override fun initView() {
        viewModel.cid.value=arguments?.getInt("cid")
        if(::adapter.isInitialized.not()){
            adapter= ContentAdapter()
        }
        tab_project_rv.layoutManager= LinearLayoutManager(activity)
        adapter.onAttachedToRecyclerView(tab_project_rv)
        tab_project_rv.adapter=adapter

        smart_ly.setOnRefreshLoadMoreListener(object: OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                initData()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.page.value=1
                initData()
            }
        })
    }

    override fun initListener() {
    }

    override fun initData() {
        viewModel.getTabProjectResponse()
    }
}