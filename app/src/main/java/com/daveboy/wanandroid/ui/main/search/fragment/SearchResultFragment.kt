package com.daveboy.wanandroid.ui.main.search.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.IndexAdapter
import com.daveboy.wanandroid.ui.main.index.IndexViewModel
import com.daveboy.wanandroid.ui.main.search.HotKeyAdapter
import com.daveboy.wanandroid.ui.main.search.SiteAdapter
import com.google.android.flexbox.JustifyContent
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.daveboy.wanandroid.ui.main.search.SearchViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchResultFragment:BaseVMFragment<SearchViewModel>() {
    private lateinit var adapter: IndexAdapter
    private lateinit var searchViewModel:SearchResultViewModel
    override fun providerVMClass(): Class<SearchViewModel>? {
        return SearchViewModel::class.java
    }

    override fun createObserver() {
        viewModel.apply {
            keyWord.observe(this@SearchResultFragment, Observer {
                searchViewModel.page.value=0
                initArticleData()
            })
        }
        searchViewModel.apply {
            articleList.observe(this@SearchResultFragment, Observer {
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

    override fun getLayoutId(): Int {
        return R.layout.fragment_index
    }

    override fun initView() {
        title_group.visibility= View.GONE
        if(::adapter.isInitialized.not()){
            adapter= IndexAdapter()
        }
        index_rv.layoutManager= LinearLayoutManager(activity)
        adapter.onAttachedToRecyclerView(index_rv)
        index_rv.adapter=adapter
    }

    override fun initListener() {
        smart_ly.setOnRefreshLoadMoreListener(object: OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                initArticleData()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                searchViewModel.page.value=0
                initArticleData()
            }
        })
    }
    private fun initArticleData(){
        searchViewModel.searchArticleList(viewModel.keyWord.value?:"")
    }
    override fun initData() {
    }

    override fun onBackPressedSupport(): Boolean {
        pop()
        return true
    }
    /**
     * 重写  进行数据共享
     */
    override fun initVM() {
        providerVMClass()?.let {
            viewModel = ViewModelProviders.of(activity!!).get(it)
        }
        searchViewModel=ViewModelProviders.of(this).get(SearchResultViewModel::class.java)
    }
}