package com.daveboy.wanandroid.ui.system.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.ArticleResponse
import kotlinx.coroutines.launch
import org.koin.core.inject

class SystemContentViewModel: BaseViewModel() {
    var page= 0
        private set
    val articleList =MutableLiveData<ViewState<ArticleResponse>>()
    private val repository: SystemContentRepository by inject()
    fun getArticleList(refresh:Boolean){
        if(refresh) page=0
        launchRequest({repository.getSystemArticleList(page,0)},articleList)
    }

}