package com.daveboy.wanandroid.ui.main.search

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.KeyboardUtils
import com.daveboy.base.BaseVMActivity
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.search.fragment.SearchFragment
import com.daveboy.wanandroid.ui.main.search.fragment.SearchResultFragment
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity:BaseVMActivity<SearchViewModel>() {
    private var searchFragment:SearchFragment?=null


    override fun startObserve() {
        viewModel.apply {
            keyWord.observe(this@SearchActivity, Observer {
                search_input.setText(it)
                search_input.setSelection(it.length)
            })
        }
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
                if(findFragment(SearchResultFragment::class.java)==null)
                    start(SearchResultFragment())
                viewModel.keyWord.value=v.text.toString()
                KeyboardUtils.hideSoftInput(this@SearchActivity)
            }
            false
        }
        search_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initData() {

    }
}