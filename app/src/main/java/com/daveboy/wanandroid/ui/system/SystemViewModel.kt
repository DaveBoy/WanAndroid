package com.daveboy.wanandroid.ui.system

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.database.ArticleResponse
import com.daveboy.wanandroid.database.BannerResponse
import com.daveboy.wanandroid.database.SystemModel
import com.daveboy.wanandroid.database.TopArticleResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SystemViewModel: BaseViewModel() {
    val systemList =MutableLiveData<List<SystemModel>>()
    private val repository= SystemRepository()
    val errorMsg=MutableLiveData<String>()

    fun getSystemList(){
        viewModelScope.launch {
            runCatching {
                repository.getSystemList()
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    LogUtils.i(it.data)
                    systemList.value=it.data
                    repository.insertSystem(it.data)
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
    }

}