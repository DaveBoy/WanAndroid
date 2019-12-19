package com.daveboy.wanandroid.ui.main.search.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.KeyboardUtils
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.entity.SearchKeyHot
import com.daveboy.wanandroid.ui.main.search.HotKeyAdapter
import com.daveboy.wanandroid.ui.main.search.SiteAdapter
import com.google.android.flexbox.JustifyContent
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.daveboy.wanandroid.ui.main.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment:BaseVMFragment<SearchViewModel>() {
    lateinit var hotKeyAdapter: HotKeyAdapter
    lateinit var siteAdapter: SiteAdapter
    override fun providerVMClass(): Class<SearchViewModel>? {
        return SearchViewModel::class.java
    }

    override fun createObserver() {
        viewModel.apply {
            hotKeyList.observe(this@SearchFragment, Observer {
                hotKeyAdapter.setNewData(it)
            })
            siteList.observe(this@SearchFragment, Observer {
                siteAdapter.setNewData(it)
            })
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun initView() {
        hotKeyAdapter= HotKeyAdapter()
        hotKeyAdapter.setOnItemClickListener { adapter, view, position ->
            start(SearchResultFragment())
            viewModel.keyWord.value=(adapter.data[position] as SearchKeyHot).name
            KeyboardUtils.hideSoftInput(activity)
        }
        siteAdapter= SiteAdapter()
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

    /**
     * 重写  进行数据共享
     */
    override fun initVM() {
        providerVMClass()?.let {
            viewModel = ViewModelProviders.of(activity!!).get(it)
        }
    }
}