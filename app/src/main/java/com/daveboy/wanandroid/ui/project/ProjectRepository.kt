package com.daveboy.wanandroid.ui.project

import com.daveboy.wanandroid.database.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query

class ProjectRepository {
    private lateinit var tabBox : Box<ProjectTab>

    suspend fun getTabList(): WanResponse<List<ProjectTab>> {
        return RetrofitManager.service.getTabList()
    }

    fun insertTab(data: List<ProjectTab>) {
        if(!::tabBox.isInitialized) {
            tabBox = BoxManager.boxStore.boxFor()
        }
        tabBox.removeAll()
        tabBox.put(data)
    }
}