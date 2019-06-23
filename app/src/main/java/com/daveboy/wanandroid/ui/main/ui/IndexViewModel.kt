package com.daveboy.wanandroid.ui.main.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.database.ArticleResponse
import kotlinx.coroutines.launch

class IndexViewModel: BaseViewModel() {
    val page= MutableLiveData(0)
    val articleList =MutableLiveData<ArticleResponse>()
    private val repository= IndexRepository()
    val errorMsg=MutableLiveData<String>()

    fun getArticleList(isFresh:Boolean=false){
        if(isFresh){
            page.value=0
        }
        viewModelScope.launch {
            runCatching {
                repository.getArticleList(page.value?:0)
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    page.value=page.value?:0+1
                    LogUtils.i(it.data)
                    articleList.value=it.data
                    repository.insertArticleResponse(it.data)
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }

    }
}