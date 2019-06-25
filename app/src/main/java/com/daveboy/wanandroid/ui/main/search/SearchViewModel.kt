package com.daveboy.wanandroid.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.database.*
import kotlinx.coroutines.launch

class SearchViewModel: BaseViewModel() {
    val hotKeyList =MutableLiveData<List<SearchKeyHot>>()
    val siteList =MutableLiveData<List<Site>>()
    val keyWord=MutableLiveData<String>()
    private val repository= SearchRepository()
    val errorMsg=MutableLiveData<String>()

    fun getHotKeyList(){
        viewModelScope.launch {
            runCatching {
                repository.getHotKeyList()
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {

                    LogUtils.i(it.data)
                    hotKeyList.value=it.data
                    repository.insertHotKey(it.data)
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
    }
    fun getSiteList(){
        viewModelScope.launch {
            runCatching {
                repository.getSiteList()
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    LogUtils.i(it.data)
                    siteList.value=it.data
                    repository.insertSite(it.data)
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
    }

}