package com.daveboy.wanandroid.ui.main.index

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse


class IndexRepository {


    suspend fun getArticleList(page:Int): WanResponse<ArticleResponse>{
        return RetrofitManager.service.getArticleList(page)
    }
    suspend fun getTopArticleList(): WanResponse<List<TopArticleResponse>>{
        return RetrofitManager.service.getTopArticleList()
    }
    suspend fun getBannerList(): WanResponse<List<BannerResponse>> {
        return RetrofitManager.service.getBannerList()
    }
}