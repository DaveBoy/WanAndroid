package com.daveboy.wanandroid.ui.main.search.fragment

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.BaseResponse

class SearchResultRepository {


    suspend fun searchArticleList(page:Int,keyWord:String): BaseResponse<ArticleResponse> {
        return RetrofitManager.service.searchArticleList(page,keyWord)
    }


}