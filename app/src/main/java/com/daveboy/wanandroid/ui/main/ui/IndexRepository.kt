package com.daveboy.wanandroid.ui.main.ui

import com.daveboy.wanandroid.database.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query

class IndexRepository {
    private lateinit var articleBox : Box<ArticleResponse>
    private lateinit var topArticleBox : Box<TopArticleResponse>
    private lateinit var bannerBox : Box<BannerResponse>

    suspend fun getArticleList(page:Int): WanResponse<ArticleResponse>{
        return RetrofitManager.service.getArticleList(page)
    }
    suspend fun getTopArticleList(): WanResponse<List<TopArticleResponse>>{
        return RetrofitManager.service.getTopArticleList()
    }
    suspend fun getBannerList(): WanResponse<List<BannerResponse>> {
        return RetrofitManager.service.getBannerList()
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
    fun insertTopArticleResponse(data: List<TopArticleResponse>) {
        if(!::topArticleBox.isInitialized) {
            topArticleBox = BoxManager.boxStore.boxFor()
        }
        topArticleBox.removeAll()
        topArticleBox.put(data)
    }
    fun insertBannerResponse(data: List<BannerResponse>) {
        if(!::bannerBox.isInitialized) {
            bannerBox = BoxManager.boxStore.boxFor()
        }
        bannerBox.removeAll()
        bannerBox.put(data)
    }
}