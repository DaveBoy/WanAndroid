package com.daveboy.wanandroid.ui.project.content

import com.daveboy.wanandroid.entity.*
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.base.BaseResponse


class ContentRepository {

    suspend fun getTabProject(page:Int,cid:Int): BaseResponse<TabProjectResponse> {
        return RetrofitManager.service.getTabProjectList(page,cid)
    }
}