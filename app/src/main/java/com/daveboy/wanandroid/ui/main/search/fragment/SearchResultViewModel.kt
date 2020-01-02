package com.daveboy.wanandroid.ui.main.search.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.ArticleResponse
import kotlinx.coroutines.launch
import org.koin.core.inject

class SearchResultViewModel: BaseViewModel() {
    var page= 0
    val articleList =MutableLiveData<ViewState<ArticleResponse>>()
    private val repository:SearchResultRepository by inject ()

    fun searchArticleList(keyWord:String,refresh:Boolean){
        if(refresh) page=0
        launchRequest({ repository.searchArticleList(page,keyWord) },articleList,success = {page++})
    }
}