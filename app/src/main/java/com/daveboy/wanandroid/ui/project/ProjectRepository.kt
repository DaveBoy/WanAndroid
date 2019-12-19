package com.daveboy.wanandroid.ui.project

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse

class ProjectRepository {

    suspend fun getTabList(): WanResponse<List<ProjectTab>> {
        return RetrofitManager.service.getTabList()
    }

}