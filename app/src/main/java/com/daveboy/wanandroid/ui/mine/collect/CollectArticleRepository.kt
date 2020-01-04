package com.daveboy.wanandroid.ui.mine.collect

import com.daveboy.base.IRepository
import com.daveboy.wanandroid.entity.CollectListResponse
import com.daveboy.wanandroid.http.ApiService
import com.daveboy.wanandroid.http.BaseResponse
import org.koin.core.inject

class CollectArticleRepository:IRepository {
    private val service by inject<ApiService>()
    suspend fun getCollectArticle(page:Int): BaseResponse<CollectListResponse> {
        return service.getCollectArticle(page)
    }
}