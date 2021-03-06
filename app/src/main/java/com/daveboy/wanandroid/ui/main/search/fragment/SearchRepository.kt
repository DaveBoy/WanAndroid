package com.daveboy.wanandroid.ui.main.search.fragment

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.BaseResponse

class SearchRepository {

    suspend fun getSiteList(): BaseResponse<List<Site>> {
        return RetrofitManager.service.getSiteList()
    }
    suspend fun getHotKeyList(): BaseResponse<List<SearchKeyHot>> {
        return RetrofitManager.service.getHotKeyList()
    }

}