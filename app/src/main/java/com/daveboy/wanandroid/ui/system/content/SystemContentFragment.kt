package com.daveboy.wanandroid.ui.system.content

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.daveboy.base.BaseVMFragment
import com.daveboy.common.util.startActivityExt
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.IndexAdapter
import com.daveboy.wanandroid.ui.main.index.viewPaper.BannerAdapter
import com.daveboy.wanandroid.ui.main.search.SearchActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*

class SystemContentFragment :BaseVMFragment<SystemContentViewModel>(){

    private val adapter by lazy { IndexAdapter() }
    override fun getLayoutId(): Int {
        return R.layout.fragment_index
    }

    override fun initView() {
        index_rv.layoutManager=LinearLayoutManager(activity)
        adapter.onAttachedToRecyclerView(index_rv)
        index_rv.adapter=adapter
    }

    override fun initListener() {
        smart_ly.setOnRefreshLoadMoreListener(object:OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                getArticleList(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                getArticleList()
            }
        })
        index_search.setOnClickListener {
            startActivityExt<SearchActivity>()
        }
    }

    override fun initData() {
        getArticleList()
    }
    private fun getArticleList(refresh:Boolean=true){
        viewModel.getArticleList(refresh)
    }
    override fun startObserve() {
        viewModel.apply {
            articleList.observe(this@SystemContentFragment, Observer {

            })
        }
        
    }


}