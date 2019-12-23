package com.daveboy.wanandroid.ui.main.index

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.ArticleResponse
import com.daveboy.wanandroid.entity.BannerResponse
import com.daveboy.wanandroid.entity.TopArticleResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.inject

class IndexViewModel: BaseViewModel() {
    private val repository:IndexRepository by inject()


    private var page= 0

    val requestState=MutableLiveData<ViewState<ArticleResponse>>()



    val topArticleList =MutableLiveData<List<TopArticleResponse>>()
    val bannerList =MutableLiveData<List<BannerResponse>>()

    val currentIndex=MutableLiveData<Int>()
    val liveAutoPlay=MutableLiveData(false)
    var job:Job?=null
    fun getArticleList(refresh:Boolean){
        if(refresh) page=0
        launchRequest({repository.getArticleList(page)},requestState,success = {page++})
    }
    fun getTopArticleList(){
        viewModelScope.launch {
            runCatching {
                repository.getTopArticleList()
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    LogUtils.i(it.data)
                    topArticleList.value=it.data
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
    }
    fun getBanner(){
        viewModelScope.launch {
            runCatching {
                repository.getBannerList()
            }.onSuccess {
                parse
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    LogUtils.i(it.data)
                    bannerList.value=it.data
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
    }
    fun startPlay(){
        if(liveAutoPlay.value==true&&job?.isActive!=true) {
            job = viewModelScope.launch {
                repeat(5) {
                    delay(1000)
                }
                currentIndex.value = currentIndex.value!! + 1
            }

        }
    }
    fun stopPlay(){
        liveAutoPlay.value=false
        if(job?.isActive==true){
            job?.cancel()

        }
    }
}