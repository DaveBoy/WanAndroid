package com.daveboy.wanandroid.ui.system.content

import androidx.lifecycle.MutableLiveData
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.ArticleResponse
import com.daveboy.wanandroid.http.repository.CollectRepository
import org.koin.core.inject

class SystemContentViewModel:BaseViewModel() {
    private val repository by inject<SystemContentRepository>()
    private val collectRepository: CollectRepository by inject()

    val articleResponse=MutableLiveData<ViewState<ArticleResponse>>()

    var page=0
        private set
    fun getSystemArticleList(refresh:Boolean,cid:Int){
        if(refresh) page=0
        launchRequest({repository.getSystemArticleList(cid,page)},articleResponse,success = {page++})
    }
    fun collect(id:Int){
        launchRequest {collectRepository.collect(id)}
    }
    fun unCollect(id:Int){
        launchRequest {collectRepository.unCollect(id)}
    }
}