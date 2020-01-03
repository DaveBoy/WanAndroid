package com.daveboy.wanandroid.ui.system.content

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.BaseResponse

class SystemContentRepository {


    suspend fun getSystemArticleList(page:Int,cid:Int): BaseResponse<ArticleResponse> {
        return RetrofitManager.service.getSystemArticleList(page,cid)
    }



}