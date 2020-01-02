package com.daveboy.wanandroid.ui.main.search.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.common.util.gone
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.index.IndexAdapter
import com.daveboy.wanandroid.ui.main.search.SearchKeyViewModel
import com.daveboy.wanandroid.util.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_index.*


class SearchResultFragment:BaseVMFragment<SearchResultViewModel>() {
    private val adapter by lazy { IndexAdapter() }
    private val searchViewModel by lazy { ViewModelProviders.of(activity!!).get(SearchKeyViewModel::class.java) }

    override fun startObserve() {
        searchViewModel.apply {
            keyWord.observe(this@SearchResultFragment, Observer {
                initArticleData()
            })
        }
        viewModel.articleList.observe(this@SearchResultFragment, Observer {
            smart_ly.finish()
            parseState(it,{
                smart_ly.setEnableLoadMore(it.over.not())
                if(viewModel.page==1){
                    adapter.setNewData(it.datas)
                }else{
                    adapter.addData(it.datas)
                }
            })

        })

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_index
    }

    override fun initView() {
        title_group.gone()
        index_rv.layoutManager= LinearLayoutManager(activity)
        adapter.onAttachedToRecyclerView(index_rv)
        index_rv.adapter=adapter
    }

    override fun initListener() {
        smart_ly.setOnRefreshLoadMoreListener(object: OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                initArticleData(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                initArticleData()
            }
        })
    }
    private fun initArticleData(refresh:Boolean=true){
        viewModel.searchArticleList(searchViewModel.keyWord.value?:"",refresh)
    }
    override fun initData() {
    }

}