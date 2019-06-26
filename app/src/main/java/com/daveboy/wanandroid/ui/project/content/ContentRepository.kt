package com.daveboy.wanandroid.ui.project.content

import com.daveboy.wanandroid.database.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import okhttp3.internal.Util.equal

class ContentRepository {
    private lateinit var tabProjectResponseBox : Box<TabProjectResponse>

    suspend fun getTabProject(page:Int,cid:Int): WanResponse<TabProjectResponse> {
        return RetrofitManager.service.getTabProjectList(page,cid)
    }

    fun insertTabProjectResponse(data: TabProjectResponse) {
        if(!::tabProjectResponseBox.isInitialized) {
            tabProjectResponseBox = BoxManager.boxStore.boxFor()
        }
        val find = tabProjectResponseBox.query {

        }.find()
        //同一个page只能存在一个
        tabProjectResponseBox.remove(find)
        tabProjectResponseBox.put(data)
    }
}