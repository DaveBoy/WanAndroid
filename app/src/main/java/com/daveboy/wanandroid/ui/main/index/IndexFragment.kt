package com.daveboy.wanandroid.ui.main.index

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.parseState
import com.daveboy.common.util.startActivityExt
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.viewPaper.BannerAdapter
import com.daveboy.wanandroid.ui.main.search.SearchActivity
import com.daveboy.wanandroid.util.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*

class IndexFragment :BaseVMFragment<IndexViewModel>(),ViewPager.OnPageChangeListener{

    private lateinit var articleAdapter:IndexAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var banner_vp:ViewPager
    private var count= 0
    override fun getLayoutId(): Int {
        return R.layout.fragment_index
    }

    override fun initView() {
        index_rv.apply {
            articleAdapter= IndexAdapter()
            articleAdapter.onAttachedToRecyclerView(this)
            layoutManager=LinearLayoutManager(activity)
            adapter=articleAdapter
        }
        bannerAdapter= BannerAdapter(activity!!)

        val header: View = LayoutInflater.from(context).inflate(R.layout.layout_banner, index_rv, false)

        banner_vp=header.findViewById(R.id.view_paper)
        banner_vp.adapter=bannerAdapter
        banner_vp.addOnPageChangeListener(this)
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
                parseState(it,{
                    smart_ly.finish()

                })


                smart_ly.setEnableLoadMore(it.over.not())
                if(page.value==1){
                    articleAdapter.setNewData(it.datas)
                    //文章列表后请求回来top被重置的问题
                    val top = topArticleList.value
                    if(top.isNullOrEmpty().not()){
                        articleAdapter.addData(0,top!!.map {
                            it.toArticle()
                        })
                    }
                }else{
                    articleAdapter.addData(it.datas)
                }
            })
            topArticleList.observe(this@IndexFragment, Observer {
                articleAdapter.addData(0,it.map {
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