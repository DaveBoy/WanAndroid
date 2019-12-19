package com.daveboy.wanandroid.ui.main.index

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.entity.ArticleResponse
import com.daveboy.wanandroid.entity.BannerResponse
import com.daveboy.wanandroid.entity.TopArticleResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IndexViewModel: BaseViewModel() {
    val page= MutableLiveData(0)
    val articleList =MutableLiveData<ArticleResponse>()
    val topArticleList =MutableLiveData<List<TopArticleResponse>>()
    val bannerList =MutableLiveData<List<BannerResponse>>()
    private val repository= IndexRepository()
    val errorMsg=MutableLiveData<String>()

    val currentIndex=MutableLiveData<Int>()
    val liveAutoPlay=MutableLiveData(false)
    var job:Job?=null
    fun getArticleList(){
        viewModelScope.launch {
            runCatching {
                repository.getArticleList(page.value?:0)
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    page.value=(page.value?:0)+1
                    LogUtils.i(it.data)
                    articleList.value=it.data
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
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