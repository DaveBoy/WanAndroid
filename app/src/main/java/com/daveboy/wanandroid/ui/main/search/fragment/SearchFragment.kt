package com.daveboy.wanandroid.ui.main.search.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.KeyboardUtils
import com.daveboy.base.BaseVMFragment
import com.daveboy.base.util.parseState
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.SearchKeyHot
import com.daveboy.wanandroid.ui.main.search.HotKeyAdapter
import com.daveboy.wanandroid.ui.main.search.SearchKeyViewModel
import com.daveboy.wanandroid.ui.main.search.SiteAdapter
import com.google.android.flexbox.JustifyContent
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment:BaseVMFragment<SearchViewModel>() {
    private val hotKeyAdapter by lazy { HotKeyAdapter() }
    private val siteAdapter by lazy { SiteAdapter() }
    private val searchViewModel by lazy { ViewModelProviders.of(activity!!).get(SearchKeyViewModel::class.java) }

    override fun startObserve() {
        viewModel.apply {
            hotKeyList.observe(this@SearchFragment, Observer {
                parseState(it,{hotKeyAdapter.setNewData(it)})

            })
            siteList.observe(this@SearchFragment, Observer {
                parseState(it,{siteAdapter.setNewData(it)})
            })
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun initView() {
        hotKeyAdapter.setOnItemClickListener { adapter, view, position ->
            searchViewModel.keyWord.value=(adapter.data[position] as SearchKeyHot).name
            searchViewModel.showDetailPage.value=true
            KeyboardUtils.hideSoftInput(activity)
        }
        siteAdapter.setOnItemClickListener { adapter, view, position ->

        }
        val layoutManager = FlexboxLayoutManager(activity)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        search_hot_rv.layoutManager = layoutManager
        search_hot_rv.adapter = hotKeyAdapter


        val layoutManager1 = FlexboxLayoutManager(activity)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        search_site_rv.layoutManager = layoutManager1
        search_site_rv.adapter = siteAdapter
    }

    override fun initListener() {
    }

    override fun initData() {
        viewModel.getHotKeyList()
        viewModel.getSiteList()
    }


}