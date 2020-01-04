package com.daveboy.wanandroid.ui.mine.collect

import androidx.lifecycle.MutableLiveData
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.Article
import com.daveboy.wanandroid.entity.CollectListResponse
import com.daveboy.wanandroid.http.repository.CollectRepository
import org.koin.core.inject

class CollectArticleViewModel:BaseViewModel() {
    private val repository:CollectArticleRepository by inject()
    private val collectRepository: CollectRepository by inject()

    var page=0
        private set

    val articleList=MutableLiveData<ViewState<CollectListResponse>>()

    fun getCollectArticle(refresh:Boolean){
        if(refresh) page=0
        launchRequest({repository.getCollectArticle(page)},articleList,success = {page++})
    }
    fun collect(id:Int){
        launchRequest {collectRepository.collect(id)}
    }
    fun unCollectInCollect(id:Int,originId:Int){
        launchRequest {collectRepository.unCollectInCollect(id,originId)}
    }
}