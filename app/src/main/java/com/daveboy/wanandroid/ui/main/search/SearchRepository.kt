package com.daveboy.wanandroid.ui.main.search

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse

class SearchRepository {

    suspend fun getSiteList(): WanResponse<List<Site>> {
        return RetrofitManager.service.getSiteList()
    }
    suspend fun getHotKeyList(): WanResponse<List<SearchKeyHot>> {
        return RetrofitManager.service.getHotKeyList()
    }

}