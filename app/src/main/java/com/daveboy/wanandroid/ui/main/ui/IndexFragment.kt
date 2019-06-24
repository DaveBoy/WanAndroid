package com.daveboy.wanandroid.ui.main.ui

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.ui.viewPaper.BannerAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*

class IndexFragment :BaseVMFragment<IndexViewModel>(),ViewPager.OnPageChangeListener{

    override fun providerVMClass()=IndexViewModel::class.java
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
       /* banner_vp.setOnTouchListener { v, event ->
                when(event.action){
                    MotionEvent.ACTION_DOWN->{
                        viewModel.stopPlay()
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL,MotionEvent.ACTION_OUTSIDE-> {
                        viewModel.liveAutoPlay.value=true
                        viewModel.startPlay()
                    }
                }
             true
        }*/
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