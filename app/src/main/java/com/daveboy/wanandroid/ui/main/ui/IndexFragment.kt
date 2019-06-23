package com.daveboy.wanandroid.ui.main.ui

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*

class IndexFragment :BaseVMFragment<IndexViewModel>(){
    override fun providerVMClass()=IndexViewModel::class.java
    private lateinit var adapter:IndexAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_index
    }

    override fun initView() {
        if(::adapter.isInitialized.not()){
            adapter= IndexAdapter()
        }
        index_rv.layoutManager=LinearLayoutManager(activity)
        adapter.onAttachedToRecyclerView(index_rv)
        index_rv.adapter=adapter
    }

    override fun initListener() {
        smart_ly.setOnRefreshLoadMoreListener(object:OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                initData()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.page.value=0
                initData()
            }

        })
    }

    override fun initData() {
        viewModel.getArticleList()
    }
    override fun createObserver() {
        viewModel.apply {
            articleList.observe(this@IndexFragment, Observer {
                smart_ly.finishRefresh()
                smart_ly.finishLoadMore()
                smart_ly.setEnableLoadMore(it.over.not())
                if(page.value==1){
                    adapter.setNewData(it.datas)
                }else{
                    adapter.addData(it.datas)
                }
            })
        }
    }
}