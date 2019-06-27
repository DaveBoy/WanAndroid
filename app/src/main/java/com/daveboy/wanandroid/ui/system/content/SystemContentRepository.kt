package com.daveboy.wanandroid.ui.system.content

import com.daveboy.wanandroid.database.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query

class SystemContentRepository {
    private lateinit var articleBox : Box<ArticleResponse>


    suspend fun getSystemArticleList(page:Int,cid:Int): WanResponse<ArticleResponse>{
        return RetrofitManager.service.getSystemArticleList(page,cid)
    }

    fun insertSystemArtic(data: ArticleResponse) {
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