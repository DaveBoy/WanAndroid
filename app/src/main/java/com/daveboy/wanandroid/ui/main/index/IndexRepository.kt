package com.daveboy.wanandroid.ui.main.index

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.BaseResponse


class IndexRepository {


    suspend fun getArticleList(page:Int): BaseResponse<ArticleResponse> {
        return RetrofitManager.service.getArticleList(page)
    }
    suspend fun getTopArticleList(): BaseResponse<List<Article>> {
        return RetrofitManager.service.getTopArticleList()
    }
    suspend fun getBannerList(): BaseResponse<List<BannerResponse>> {
        return RetrofitManager.service.getBannerList()
    }
}