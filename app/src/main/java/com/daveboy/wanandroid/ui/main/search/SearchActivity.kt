package com.daveboy.wanandroid.ui.main.search

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.blankj.utilcode.util.KeyboardUtils
import com.daveboy.base.BaseActivity
import com.daveboy.base.BaseVMActivity
import com.daveboy.base.BaseVMFragment
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.search.fragment.SearchFragment
import com.daveboy.wanandroid.ui.main.search.fragment.SearchResultFragment
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity:BaseVMActivity<SearchViewModel>() {
    private var searchFragment:SearchFragment?=null
    override fun providerVMClass(): Class<SearchViewModel>? {
        return SearchViewModel::class.java
    }

    override fun createObserver() {
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initView() {
        searchFragment=findFragment(SearchFragment::class.java)
        if(searchFragment==null) {
            searchFragment=SearchFragment()
            loadRootFragment(R.id.search_ly,searchFragment!!)
        }
    }

    override fun initListener() {

        search_input.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                start(SearchResultFragment())
                viewModel.keyWord.value=v.text.toString()
                KeyboardUtils.hideSoftInput(this@SearchActivity)
            }
            false
        }
    }

    override fun initData() {

    }
}