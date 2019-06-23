package com.daveboy.wanandroid.ui.main.ui

import com.daveboy.wanandroid.database.ArticleResponse
import com.daveboy.wanandroid.database.BoxManager
import com.daveboy.wanandroid.database.UserModel
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse
import io.objectbox.Box
import io.objectbox.kotlin.boxFor

class IndexRepository {
    private lateinit var box : Box<ArticleResponse>

    suspend fun getArticleList(page:Int): WanResponse<ArticleResponse>{
        return RetrofitManager.service.getArticleList(page)
    }
    fun insertArticleResponse(data: ArticleResponse) {
        if(!::box.isInitialized) {
            box = BoxManager.boxStore.boxFor()
        }
        box.removeAll()
        box.put(data)
    }
}