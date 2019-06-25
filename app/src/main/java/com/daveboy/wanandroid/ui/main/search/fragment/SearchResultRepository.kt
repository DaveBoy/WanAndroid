package com.daveboy.wanandroid.ui.main.search.fragment

import com.daveboy.wanandroid.database.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query

class SearchResultRepository {
    private lateinit var articleBox : Box<ArticleResponse>
    private lateinit var topArticleBox : Box<TopArticleResponse>
    private lateinit var bannerBox : Box<BannerResponse>

    suspend fun searchArticleList(page:Int,keyWord:String): WanResponse<ArticleResponse>{
        return RetrofitManager.service.searchArticleList(page,keyWord)
    }

    fun insertArticleResponse(data: ArticleResponse) {
        if(!::articleBox.isInitialized) {
            articleBox = BoxManager.boxStore.boxFor()
        }
        val find = articleBox.query {
            equal(ArticleResponse_.curPage, data.curPage.toLong())
        }.find()
        //同一个page只能存在一个
        articleBox.remove(find)
        articleBox.put(data)
    }
}