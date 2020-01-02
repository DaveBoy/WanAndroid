package com.daveboy.wanandroid.ui.main.search.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.*
import kotlinx.coroutines.launch
import org.koin.core.inject

class SearchViewModel: BaseViewModel() {
    val hotKeyList =MutableLiveData<ViewState<List<SearchKeyHot>>>()
    val siteList =MutableLiveData<ViewState<List<Site>>>()
    private val repository: SearchRepository by inject()

    fun getHotKeyList(){
        launchRequest({repository.getHotKeyList()},hotKeyList)
    }
    fun getSiteList(){
        launchRequest({repository.getSiteList()},siteList)
    }

}