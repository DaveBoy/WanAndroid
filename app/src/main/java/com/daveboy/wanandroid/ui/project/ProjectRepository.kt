package com.daveboy.wanandroid.ui.project

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.base.BaseResponse

class ProjectRepository {

    suspend fun getTabList(): BaseResponse<List<ProjectTab>> {
        return RetrofitManager.service.getTabList()
    }

}