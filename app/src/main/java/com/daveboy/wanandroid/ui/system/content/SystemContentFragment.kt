package com.daveboy.wanandroid.ui.system.content

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.IndexAdapter
import com.daveboy.wanandroid.ui.main.index.viewPaper.BannerAdapter
import com.daveboy.wanandroid.ui.main.search.SearchActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*

class SystemContentFragment :BaseVMFragment<SystemContentViewModel>(){

    override fun providerVMClass()= SystemContentViewModel::class.java
    private lateinit var adapter: IndexAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var banner_vp:ViewPager
    private var count= 0
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
        index_search.setOnClickListener {
            startActivity(Intent().apply {
                setClass(activity!!, SearchActivity::class.java)
            })
        }
    }

    override fun initData() {
        viewModel.getArticleList()
    }
    override fun createObserver() {
        viewModel.apply {
            articleList.observe(this@SystemContentFragment, Observer {
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