package com.daveboy.wanandroid.ui.system.content

import com.daveboy.base.IRepository
import com.daveboy.wanandroid.entity.ArticleResponse
import com.daveboy.wanandroid.http.ApiService
import com.daveboy.wanandroid.http.BaseResponse
import org.koin.core.inject

class SystemContentRepository:IRepository {
    val service by inject<ApiService>()

    suspend fun getSystemArticleList(cid:Int, page:Int): BaseResponse<ArticleResponse> {
        return service.getSystemArticleList(page,cid)
    }
}