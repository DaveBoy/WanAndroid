package com.daveboy.wanandroid.ui.system

import com.daveboy.wanandroid.entity.SystemModel
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.base.BaseResponse

class SystemRepository {



    suspend fun getSystemList(): BaseResponse<List<SystemModel>> {
        return RetrofitManager.service.getSystemList()
    }


}