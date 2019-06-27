package com.daveboy.wanandroid.ui.system.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.database.ArticleResponse
import com.daveboy.wanandroid.database.BannerResponse
import com.daveboy.wanandroid.database.TopArticleResponse
import com.daveboy.wanandroid.ui.main.index.IndexRepository
import com.daveboy.wanandroid.ui.system.SystemRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SystemContentViewModel: BaseViewModel() {
    val page= MutableLiveData(0)
    val articleList =MutableLiveData<ArticleResponse>()
    private val repository= SystemContentRepository()
    val errorMsg=MutableLiveData<String>()


    fun getArticleList(){
        viewModelScope.launch {
            runCatching {
                repository.getSystemArticleList(page.value?:0,0)
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    page.value=(page.value?:0)+1
                    LogUtils.i(it.data)
                    articleList.value=it.data
                    //repository.insertArticleResponse(it.data)
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
    }

}