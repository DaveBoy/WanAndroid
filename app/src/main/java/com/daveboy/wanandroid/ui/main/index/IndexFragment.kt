package com.daveboy.wanandroid.ui.main.index

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.common.util.startActivityExt
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.viewPaper.BannerAdapter
import com.daveboy.wanandroid.ui.main.search.SearchActivity
import com.daveboy.wanandroid.util.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*

class IndexFragment :BaseVMFragment<IndexViewModel>(){

    private  val articleAdapter:ArticleAdapter by lazy { ArticleAdapter() }
    private  val bannerAdapter: BannerAdapter by lazy { BannerAdapter() }
    private lateinit var banner_vp:ViewPager2
    override fun getLayoutId(): Int {
        return R.layout.fragment_index
    }

    override fun initView() {
        index_rv.apply {
            articleAdapter.onAttachedToRecyclerView(this)
            layoutManager=LinearLayoutManager(activity)
            adapter=articleAdapter
        }
        val header: View = LayoutInflater.from(context).inflate(R.layout.layout_banner, index_rv, false)

        banner_vp=header.findViewById(R.id.view_paper)
        banner_vp.adapter=bannerAdapter
        banner_vp.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                val index = viewModel.currentIndex.value
                val count=bannerAdapter.itemCount
                when(state){
                    ViewPager2.SCROLL_STATE_IDLE->{//over
                        if ( index== 0) {
                            banner_vp.setCurrentItem(count, false)
                        } else if (index == count + 1) {
                            banner_vp.setCurrentItem(1, false)
                        }
                        viewModel.liveAutoPlay=true
                        viewModel.startPlay()
                    }
                    ViewPager2.SCROLL_STATE_DRAGGING->{//start
                        if(index==count+1){
                            banner_vp.setCurrentItem(1,false)
                        }else if(index==0){
                            banner_vp.setCurrentItem(count,false)
                        }
                        viewModel.stopPlay()
                    }
                    else->{}
                    //change
                }
            }
            override fun onPageSelected(position: Int) {
                viewModel.currentIndex.value=position
            }
        })
        articleAdapter.addHeaderView(header)
    }

    override fun initListener() {
        smart_ly.setOnRefreshLoadMoreListener(object:OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                getArticle()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                getArticle(refresh = true)
            }
        })
        index_search.setOnClickListener {
            startActivityExt<SearchActivity>()
        }
    }

    override fun initData() {
        getArticle(true)
        viewModel.getBanner()
        viewModel.getTopArticleList()
    }
    private fun getArticle(refresh:Boolean=false){
        viewModel.getArticleList(refresh)
    }
    override fun startObserve() {
        viewModel.apply {
            requestState.observe(this@IndexFragment, Observer {
                smart_ly.finish()
                parseState(it,{
                    if(viewModel.page==1){
                        articleAdapter.setNewData(it.datas)
                    }else{
                        articleAdapter.addData(it.datas)
                    }
                })
            })
            topArticleList.observe(this@IndexFragment, Observer {
                //TODO 这里可能会导致bug
                parseState(it,{
                    articleAdapter.addData(0,it)
                })

            })
            bannerList.observe(this@IndexFragment, Observer {
                parseState(it,{
                    bannerAdapter.data = it.toMutableList()
                    banner_vp.currentItem=1
                    bannerAdapter.notifyDataSetChanged()
                })

            })
            currentIndex.observe(this@IndexFragment, Observer {
                if(it<bannerAdapter.itemCount)
                    banner_vp.currentItem=it
            })
        }
        
    }

    //==================    ==================

}