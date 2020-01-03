package com.daveboy.wanandroid.ui.main.index

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.Article
import com.daveboy.wanandroid.entity.ArticleResponse
import com.daveboy.wanandroid.entity.BannerResponse
import com.daveboy.wanandroid.http.repository.CollectRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.inject

class IndexViewModel: BaseViewModel() {
    private val repository:IndexRepository by inject()
    private val collectRepository:CollectRepository by inject()


    var page= 0
    val requestState=MutableLiveData<ViewState<ArticleResponse>>()
    val topArticleList =MutableLiveData<ViewState<List<Article>>>()
    val bannerList =MutableLiveData<ViewState<List<BannerResponse>>>()

    val currentIndex=MutableLiveData(0)

    var liveAutoPlay=false
    private var job:Job?=null
    fun getArticleList(refresh:Boolean){
        if(refresh) page=0
        launchRequest({repository.getArticleList(page)},requestState,success = {page++})
    }
    fun getTopArticleList(){
        launchRequest({repository.getTopArticleList()},topArticleList)
    }
    fun collect(id:Int){
        launchRequest {collectRepository.collect(id)}
    }
    fun unCollect(id:Int){
        launchRequest {collectRepository.unCollect(id)}
    }
    fun getBanner(){
        launchRequest({repository.getBannerList()},bannerList)
    }
    fun startPlay(){
        if(liveAutoPlay&&job?.isActive!=true) {
            job = viewModelScope.launch {
                repeat(5) {
                    delay(1000)
                }
                currentIndex.value = currentIndex.value!! + 1
            }

        }
    }
    fun stopPlay(){
        liveAutoPlay=false
        if(job?.isActive==true){
            job?.cancel()
        }
    }
}