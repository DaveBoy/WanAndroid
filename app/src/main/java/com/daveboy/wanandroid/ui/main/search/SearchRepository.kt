package com.daveboy.wanandroid.ui.main.search

import com.daveboy.wanandroid.database.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse
import io.objectbox.Box
import io.objectbox.kotlin.boxFor

class SearchRepository {
    private lateinit var siteBox : Box<Site>
    private lateinit var hotKeyBox : Box<SearchKeyHot>


    suspend fun getSiteList(): WanResponse<List<Site>> {
        return RetrofitManager.service.getSiteList()
    }
    suspend fun getHotKeyList(): WanResponse<List<SearchKeyHot>> {
        return RetrofitManager.service.getHotKeyList()
    }
    fun insertSite(data: List<Site>) {
        if(!::siteBox.isInitialized) {
            siteBox = BoxManager.boxStore.boxFor()
        }
        siteBox.removeAll()
        siteBox.put(data)
    }

    fun insertHotKey(data: List<SearchKeyHot>) {
        if(!::hotKeyBox.isInitialized) {
            hotKeyBox = BoxManager.boxStore.boxFor()
        }
        hotKeyBox.removeAll()
        hotKeyBox.put(data)
    }
}