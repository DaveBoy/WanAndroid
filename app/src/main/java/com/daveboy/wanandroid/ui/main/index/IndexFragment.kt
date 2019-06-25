package com.daveboy.wanandroid.ui.main.index

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.viewPaper.BannerAdapter
import com.daveboy.wanandroid.ui.main.search.SearchActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*

class IndexFragment :BaseVMFragment<IndexViewModel>(),ViewPager.OnPageChangeListener{

    override fun providerVMClass()= IndexViewModel::class.java
    private lateinit var adapter:IndexAdapter
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

        if(::bannerAdapter.isInitialized.not()){
            bannerAdapter= BannerAdapter(activity!!)
        }
        val header: View = LayoutInflater.from(context).inflate(R.layout.layout_banner, index_rv, false)

        banner_vp=header.findViewById(R.id.view_paper)
        banner_vp.adapter=bannerAdapter
        banner_vp.addOnPageChangeListener(this)
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
        index_search.setOnClickListener {
            startActivity(Intent().apply {
                setClass(activity!!, SearchActivity::class.java)
            })
        }
    }

    override fun initData() {
        viewModel.getArticleList()
        if( viewModel.page.value==0){
            viewModel.getBanner()
            viewModel.getTopArticleList()
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
                    //文章列表后请求回来top被重置的问题
                    val top = topArticleList.value
                    if(top.isNullOrEmpty().not()){
                        adapter.addData(0,top!!.map {
                            it.toArticle()
                        })
                    }
                }else{
                    adapter.addData(it.datas)
                }
            })
            topArticleList.observe(this@IndexFragment, Observer {
                adapter.addData(0,it.map {
                    it.toArticle()
                })
            })
            bannerList.observe(this@IndexFragment, Observer {
                count=it.size
                bannerAdapter.setData(it.toMutableList())
                bannerAdapter.notifyDataSetChanged()
                banner_vp.currentItem=1
            })
            currentIndex.observe(this@IndexFragment, Observer {
                if(it<bannerAdapter.count)
                    banner_vp.currentItem=it
            })
        }
        
    }

    //==================    ==================
    override fun onPageScrollStateChanged(state: Int) {
        val index = viewModel.currentIndex.value
        when(state){
            0->{//over
                if ( index== 0) {
                    banner_vp.setCurrentItem(count, false)
                } else if (index == count + 1) {
                    banner_vp.setCurrentItem(1, false)
                }
                viewModel.liveAutoPlay.value=true
                viewModel.startPlay()
            }
            1->{//start
                if(index==count+1){
                    banner_vp.setCurrentItem(1,false)
                }else if(index==0){
                    banner_vp.setCurrentItem(count,false)
                }
                viewModel.stopPlay()
            }
            //change
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        viewModel.currentIndex.value=position
    }
}