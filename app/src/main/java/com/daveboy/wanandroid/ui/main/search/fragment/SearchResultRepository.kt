package com.daveboy.wanandroid.ui.main.search.fragment

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse

class SearchResultRepository {


    suspend fun searchArticleList(page:Int,keyWord:String): WanResponse<ArticleResponse>{
        return RetrofitManager.service.searchArticleList(page,keyWord)
    }


}