package com.daveboy.wanandroid.ui.main.search

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.KeyboardUtils
import com.daveboy.base.BaseVMActivity
import com.daveboy.common.listener.textWatcher
import com.daveboy.common.util.gone
import com.daveboy.common.util.isGone
import com.daveboy.common.util.isVisible
import com.daveboy.common.util.visible
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.search.fragment.SearchFragment
import com.daveboy.wanandroid.ui.main.search.fragment.SearchResultFragment
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity:BaseVMActivity<SearchKeyViewModel>() {
    private val searchFragment by lazy { SearchFragment() }
    private val searchDetailFragment by lazy { SearchResultFragment() }


    override fun startObserve() {
        viewModel.apply {
            keyWord.observe(this@SearchActivity, Observer {
                search_input.setText(it)
                search_input.setSelection(it.length)
            })
            showDetailPage.observe(this@SearchActivity, Observer {
                search_ly.isGone=it
                search_detail_ly.isGone=!it
            })
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initView() {
        supportFragmentManager.beginTransaction().apply {
            this.replace(R.id.search_ly,searchFragment)
            this.replace(R.id.search_detail_ly,searchDetailFragment)
        }.commitAllowingStateLoss()
        viewModel.showDetailPage.value=false
    }

    override fun initListener() {

        search_input.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.keyWord.value=v.text.toString()
                KeyboardUtils.hideSoftInput(this@SearchActivity)
                viewModel.showDetailPage.value=true
            }
            false
        }
        search_input.textWatcher {
            afterTextChanged {
                if(it?.toString().isNullOrEmpty()){
                    viewModel.showDetailPage.value=false
                }
            }
        }
        search_back.setOnClickListener {
            if(viewModel.showDetailPage.value!!){
                viewModel.showDetailPage.value=false
                return@setOnClickListener
            }
            onBackPressed()
        }
    }

    override fun initData() {

    }
}