package com.daveboy.wanandroid.ui.main.ui

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.ui.viewPaper.BannerAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*

class IndexFragment :BaseVMFragment<IndexViewModel>(){
    override fun providerVMClass()=IndexViewModel::class.java
    private lateinit var adapter:IndexAdapter
    private lateinit var bannerAdapter: BannerAdapter


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

        if(::bannerAdapter.isInitialized.not()){
            bannerAdapter= BannerAdapter(activity!!)
        }
        val header: View = LayoutInflater.from(context).inflate(R.layout.layout_banner, index_rv, false)

        header.findViewById<ViewPager>(R.id.view_paper).adapter=bannerAdapter
        adapter.addHeaderView(header)
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
        if( viewModel.page.value==0){
            viewModel.getBanner()
        }
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
            bannerList.observe(this@IndexFragment, Observer {
                bannerAdapter.data=it
                bannerAdapter.notifyDataSetChanged()
            })
        }
        
    }
}