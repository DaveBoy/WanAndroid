package com.daveboy.wanandroid.http.repository

import com.daveboy.base.IRepository
import com.daveboy.wanandroid.http.ApiService
import com.daveboy.wanandroid.http.BaseResponse
import org.koin.core.inject

class CollectRepository:IRepository {
    val service:ApiService by inject()
    suspend fun collect(id:Int): BaseResponse<String?> {
        return service.collect(id)
    }
    suspend fun unCollect(id:Int): BaseResponse<String?> {
        return service.unCollect(id)
    }
    suspend fun unCollectInCollect(id:Int,originId:Int): BaseResponse<String?> {
        return service.unCollectInCollect(id,originId)
    }
}