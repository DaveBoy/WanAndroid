package com.daveboy.wanandroid.ui.main.search.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.entity.ArticleResponse
import kotlinx.coroutines.launch

class SearchResultViewModel: BaseViewModel() {
    val page= MutableLiveData(0)
    val articleList =MutableLiveData<ArticleResponse>()
    private val repository= SearchResultRepository()
    val errorMsg=MutableLiveData<String>()

    fun searchArticleList(keyWord:String){
        viewModelScope.launch {
            runCatching {
                repository.searchArticleList(page.value?:0,keyWord)
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
}